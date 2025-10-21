package app.domain.services;

import java.sql.Date;
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

    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private MedicalOrderPort orderPort;

    public void create(MedicalOrder order) throws Exception {
        Employee doctor = employeePort.findByDocument(order.getDoctor());
        if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
            throw new BusinessException("Las órdenes solo las pueden crear médicos");
        }
        Patient patient = patientPort.findByDocument(order.getPatient());
        if (patient == null) {
            throw new BusinessException("La orden debe asociarse a un paciente registrado");
        }
        if (order.getId() != null) {
            if (orderPort.findById(order) != null) {
                throw new BusinessException("Ya existe una orden con ese identificador");
            }
        }
        boolean hasDiagnosticAid = false;
        Set<Integer> usedItemNumbers = new HashSet<>();
        for (OrderItem item : order.getItems()) {
            if (!usedItemNumbers.add(item.getItemNumber())) {
                throw new BusinessException("No se puede repetir el número de ítem dentro de la misma orden");
            }
            if (item.getType() == OrderItemType.DIAGNOSTIC_AID) {
                hasDiagnosticAid = true;
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
        order.setCreationDate(new Date(System.currentTimeMillis()));
        orderPort.save(order);
    }
}