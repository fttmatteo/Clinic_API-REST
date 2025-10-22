package app.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.OrderItem;
import app.domain.model.Patient;
import app.domain.model.enums.OrderItemType;
import app.domain.model.enums.Role;
import app.domain.ports.EmployeePort;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.PatientPort;
import app.domain.model.Medicine;
import app.domain.model.Procedure;
import app.domain.model.DiagnosticAid;

/**
 * Servicio de dominio para la creación de órdenes médicas. Aplica las reglas
 * del negocio antes de persistir la orden. Las reglas incluyen: validar
 * médico y paciente, verificar unicidad del número de orden, asegurar que
 * los ítems de la orden cumplen las restricciones (no mezclar ayudas
 * diagnósticas con medicamentos o procedimientos y no repetir números de
 * ítem). Al finalizar se asigna la fecha de creación.
 */
@Service
public class CreateMedicalOrder {

    /**
     * Registro de claves de idempotencia para la creación de órdenes. La clave se
     * construye a partir del identificador del médico, del paciente y de la
     * combinación de ítems (tipo y código de referencia) de la orden. Almacena
     * la marca de tiempo del último procesamiento para evitar duplicar órdenes
     * por múltiples envíos en un intervalo corto de tiempo. Este mapa se
     * comparte por toda la aplicación mientras esté en memoria.
     */
    private static final java.util.concurrent.ConcurrentHashMap<String, Long> recentOrderKeys = new java.util.concurrent.ConcurrentHashMap<>();

    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private MedicalOrderPort orderPort;
    @Autowired
    private InventoryService inventoryService;

    public void create(MedicalOrder order) throws Exception {
        try {
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
            String idempotencyKey = (doctorDocKey != null ? doctorDocKey : "") + "|" +
                                    (patientDocKey != null ? patientDocKey : "") + "|" +
                                    String.join(",", itemKeys);
            long now = System.currentTimeMillis();
            Long last = recentOrderKeys.get(idempotencyKey);
            if (last != null && (now - last) < 5 * 60 * 1000) {
                throw new BusinessException("Ya existe una orden con la misma información enviada recientemente");
            }
            recentOrderKeys.put(idempotencyKey, now);
        } catch (Exception ex) {
        }
        Employee doctor = employeePort.findByDocument(order.getDoctor());
        if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
            throw new BusinessException("Las órdenes solo las pueden crear médicos");
        }
        Patient patient = patientPort.findByDocument(order.getPatient());
        if (patient == null) {
            throw new BusinessException("La orden debe asociarse a un paciente registrado");
        }
        order.setId(null);
        boolean hasDiagnosticAid = false;
        Set<Integer> usedItemNumbers = new HashSet<>();
        Set<String> usedItemTypeAndId = new HashSet<>();
        for (OrderItem item : order.getItems()) {
            if (!usedItemNumbers.add(item.getItemNumber())) {
                throw new BusinessException("No se puede repetir el número de ítem dentro de la misma orden");
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
                    throw new BusinessException("No se puede recetar el mismo medicamento más de una vez en la misma orden");
                }
                item.setName(med.getName());
                item.setCost(med.getCost());
            } else if (item.getType() == OrderItemType.PROCEDURE) {
                Procedure proc = inventoryService.findProcedureById(item.getName());
                if (proc == null) {
                    throw new BusinessException("No existe el procedimiento con identificador " + item.getName());
                }
                String key = item.getType().name() + ":" + proc.getId();
                if (!usedItemTypeAndId.add(key)) {
                    throw new BusinessException("No se puede recetar el mismo procedimiento más de una vez en la misma orden");
                }
                item.setName(proc.getName());
                if (item.getQuantity() != null && item.getQuantity() > 0) {
                    item.setCost(proc.getCost() * item.getQuantity());
                } else {
                    item.setCost(proc.getCost());
                }
            } else if (item.getType() == OrderItemType.DIAGNOSTIC_AID) {
                DiagnosticAid aid = inventoryService.findDiagnosticAidById(item.getName());
                if (aid == null) {
                    throw new BusinessException("No existe la ayuda diagnóstica con identificador " + item.getName());
                }
                String key = item.getType().name() + ":" + aid.getId();
                if (!usedItemTypeAndId.add(key)) {
                    throw new BusinessException("No se puede recetar la misma ayuda diagnóstica más de una vez en la misma orden");
                }
                item.setName(aid.getName());
                if (item.getQuantity() != null && item.getQuantity() > 0) {
                    item.setCost(aid.getCost() * item.getQuantity());
                } else {
                    item.setCost(aid.getCost());
                }
            }
        }
        int expectedSize = order.getItems().size();
        for (int i = 1; i <= expectedSize; i++) {
            if (!usedItemNumbers.contains(i)) {
                throw new BusinessException("Los números de ítem deben empezar en 1 y ser consecutivos");
            }
        }
        if (hasDiagnosticAid) {
            for (OrderItem item : order.getItems()) {
                if (item.getType() == OrderItemType.MEDICINE || item.getType() == OrderItemType.PROCEDURE) {
                    throw new BusinessException("Cuando se receta una ayuda diagnóstica no se pueden recetar procedimientos ni medicamentos");
                }
            }
        }
        order.setDoctor(doctor);
        order.setPatient(patient);
        orderPort.save(order);
    }
}