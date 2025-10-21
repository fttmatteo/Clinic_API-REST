package app.domain.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.ports.EmployeePort;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.MedicalRecordPort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio encargado de crear registros clínicos (historias médicas).
 * Valida que el médico y el paciente existan, y que la orden asociada sea
 * válida en caso de que exista. También establece la fecha actual y marca
 * el registro como activo.
 */
@Service
public class CreateMedicalRecord {

    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private MedicalOrderPort orderPort;
    @Autowired
    private MedicalRecordPort recordPort;

    public void create(MedicalRecord record) throws Exception {
        Patient patient = patientPort.findByDocument(record.getPatient());
        if (patient == null) {
            throw new BusinessException("La historia clínica debe estar asociada a un paciente válido");
        }
        Employee doctor = employeePort.findByDocument(record.getDoctor());
        if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
            throw new BusinessException("La historia clínica debe ser registrada por un médico válido");
        }
        MedicalOrder order = record.getMedicalOrder();
        if (order != null) {
            order = orderPort.findById(order);
            if (order == null || order.getPatient().getId() != patient.getId()) {
                throw new BusinessException("La historia clínica debe tener una orden válida asociada");
            }
            record.setMedicalOrder(order);
        }
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setDateTime(new Timestamp(System.currentTimeMillis()));
        record.setActive(true);
        recordPort.save(record);
    }
}