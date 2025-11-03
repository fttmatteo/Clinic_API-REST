package app.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.DiagnosticAid;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.Medicine;
import app.domain.model.OrderItem;
import app.domain.model.Patient;
import app.domain.model.Procedure;
import app.domain.model.enums.OrderItemType;
import app.domain.model.enums.Role;
import app.domain.ports.EmployeePort;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio para la creacion de ordenes medicas. Aplica las reglas
 * del negocio antes de persistir la orden. Las reglas incluyen: validar
 * medico y paciente, verificar unicidad del numero de orden, asegurar que
 * los items de la orden cumplen las restricciones (no mezclar ayudas
 * diagnosticas con medicamentos o procedimientos y no repetir numeros de
 * item). Al finalizar se asigna la fecha de creacion.
 */
@Service
public class CreateMedicalOrder {

    /**
     * Registro de claves de idempotencia para la creacion de ordenes. La clave se
     * construye a partir del identificador del medico, del paciente y de la
     * combinacion de items (tipo y codigo de referencia) de la orden. Almacena
     * la marca de tiempo del ultimo procesamiento para evitar duplicar ordenes
     * por multiples envios en un intervalo corto de tiempo. Este mapa se
     * comparte por toda la aplicacion mientras esta en memoria.
     */
    private static final java.util.concurrent.ConcurrentHashMap<String, Long> recentOrderKeys = new java.util.concurrent.ConcurrentHashMap<>();
    private static final long IDEMPOTENCY_WINDOW_MILLIS = 5 * 60 * 1000;

    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private MedicalOrderPort orderPort;
    @Autowired
    private InventoryService inventoryService;

    public void create(MedicalOrder order) throws Exception {
        String idempotencyKey = buildIdempotencyKey(order);
        long now = System.currentTimeMillis();
        Long previousTimestamp = recentOrderKeys.putIfAbsent(idempotencyKey, now);
        if (previousTimestamp != null) {
            if ((now - previousTimestamp) < IDEMPOTENCY_WINDOW_MILLIS) {
                throw new BusinessException("Ya existe una orden con la misma informacion enviada recientemente");
            }
            recentOrderKeys.put(idempotencyKey, now);
        }
        boolean processedSuccessfully = false;
        try {
            Employee doctor = employeePort.findByDocument(order.getDoctor());
            if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
                throw new BusinessException("Las ordenes solo las pueden crear medicos");
            }
            Patient patient = patientPort.findByDocument(order.getPatient());
            if (patient == null) {
                throw new BusinessException("La orden debe asociarse a un paciente registrado");
            }
            order.setId(null);

            if (order.getOrderNumber() == null || order.getOrderNumber().isEmpty()) {
                String newOrderNumber;
                do {
                    int num = (int) (Math.random() * 1_000_000);
                    newOrderNumber = String.format("%06d", num);
                } while (orderPort.findByOrderNumber(newOrderNumber) != null);
                order.setOrderNumber(newOrderNumber);
            }
            boolean hasDiagnosticAid = false;
            Set<Integer> usedItemNumbers = new HashSet<>();
            Set<String> usedItemTypeAndId = new HashSet<>();
            for (OrderItem item : order.getItems()) {
                if (!usedItemNumbers.add(item.getItemNumber())) {
                    throw new BusinessException("No se puede repetir el numero de item dentro de la misma orden");
                }
                if (item.getType() == OrderItemType.DIAGNOSTIC_AID) {
                    hasDiagnosticAid = true;
                }
                if (item.getType() == OrderItemType.MEDICINE) {
                    Medicine med = inventoryService.findMedicineById(item.getName());
                    if (med == null) {
                        throw new BusinessException("No existe el medicamento con identificador " + item.getName());
                    }
                    String key = item.getType().name() + ":" + med.getId();
                    if (!usedItemTypeAndId.add(key)) {
                        throw new BusinessException("No se puede recetar el mismo medicamento mas de una vez en la misma orden");
                    }
                    item.setName(med.getName());
                    item.setDose(med.getDefaultDose());
                    item.setTreatmentDuration(med.getDefaultTreatmentDuration());
                    item.setQuantity(null);
                    item.setFrequency(null);
                    item.setRequiresSpecialist(Boolean.FALSE);
                    item.setSpecialistTypeId(null);
                    item.setCost(med.getCost());
                } else if (item.getType() == OrderItemType.PROCEDURE) {
                    Procedure proc = inventoryService.findProcedureById(item.getName());
                    if (proc == null) {
                        throw new BusinessException("No existe el procedimiento con identificador " + item.getName());
                    }
                    String key = item.getType().name() + ":" + proc.getId();
                    if (!usedItemTypeAndId.add(key)) {
                        throw new BusinessException("No se puede recetar el mismo procedimiento mas de una vez en la misma orden");
                    }
                    item.setName(proc.getName());
                    Integer defaultQuantity = proc.getDefaultQuantity();
                    item.setQuantity(defaultQuantity);
                    item.setFrequency(proc.getDefaultFrequency());
                    Boolean defaultRequiresSpecialist = proc.getDefaultRequiresSpecialist();
                    item.setRequiresSpecialist(defaultRequiresSpecialist);
                    item.setDose(null);
                    item.setTreatmentDuration(null);
                    item.setSpecialistTypeId(null);
                    if (defaultQuantity != null && defaultQuantity > 0) {
                        item.setCost(proc.getCost() * defaultQuantity);
                    } else {
                        item.setCost(proc.getCost());
                    }
                } else if (item.getType() == OrderItemType.DIAGNOSTIC_AID) {
                    DiagnosticAid aid = inventoryService.findDiagnosticAidById(item.getName());
                    if (aid == null) {
                        throw new BusinessException("No existe la ayuda diagnostica con identificador " + item.getName());
                    }
                    String key = item.getType().name() + ":" + aid.getId();
                    if (!usedItemTypeAndId.add(key)) {
                        throw new BusinessException("No se puede recetar la misma ayuda diagnostica mas de una vez en la misma orden");
                    }
                    item.setName(aid.getName());
                    Integer defaultQuantity = aid.getDefaultQuantity();
                    item.setQuantity(defaultQuantity);
                    Boolean defaultRequiresSpecialist = aid.getDefaultRequiresSpecialist();
                    item.setRequiresSpecialist(defaultRequiresSpecialist);
                    item.setFrequency(null);
                    item.setDose(null);
                    item.setTreatmentDuration(null);
                    item.setSpecialistTypeId(null);
                    if (defaultQuantity != null && defaultQuantity > 0) {
                        item.setCost(aid.getCost() * defaultQuantity);
                    } else {
                        item.setCost(aid.getCost());
                    }
                }
            }
            int expectedSize = order.getItems().size();
            for (int i = 1; i <= expectedSize; i++) {
                if (!usedItemNumbers.contains(i)) {
                    throw new BusinessException("Los numeros de item deben empezar en 1 y ser consecutivos");
                }
            }
            if (hasDiagnosticAid) {
                for (OrderItem item : order.getItems()) {
                    if (item.getType() == OrderItemType.MEDICINE || item.getType() == OrderItemType.PROCEDURE) {
                        throw new BusinessException("Cuando se receta una ayuda diagnostica no se pueden recetar procedimientos ni medicamentos");
                    }
                }
            }
            order.setDoctor(doctor);
            order.setPatient(patient);
            orderPort.save(order);
            processedSuccessfully = true;
        } finally {
            if (processedSuccessfully) {
                recentOrderKeys.put(idempotencyKey, System.currentTimeMillis());
            } else {
                if (previousTimestamp == null) {
                    recentOrderKeys.remove(idempotencyKey, now);
                } else {
                    recentOrderKeys.put(idempotencyKey, previousTimestamp);
                }
            }
        }
    }

    private static String buildIdempotencyKey(MedicalOrder order) {
        Long doctorDocKey = order.getDoctor() != null ? order.getDoctor().getDocument() : null;
        Long patientDocKey = order.getPatient() != null ? order.getPatient().getDocument() : null;
        java.util.List<String> itemKeys = new java.util.ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                String typeKey = item.getType() != null ? item.getType().name() : "";
                String ref = item.getName() != null ? item.getName() : "";
                itemKeys.add(typeKey + ":" + ref);
            }
        }
        java.util.Collections.sort(itemKeys);
        return (doctorDocKey != null ? doctorDocKey : "") + "|" +
                (patientDocKey != null ? patientDocKey : "") + "|" +
                String.join(",", itemKeys);
    }
}
