package app.domain.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.VitalSignsRecord;
import app.domain.model.enums.Role;
import app.domain.ports.EmployeePort;
import app.domain.ports.PatientPort;
import app.domain.ports.VitalSignsPort;

/**
 * Servicio de dominio para la creación de registros de signos vitales. Se
 * asegura de que la persona que registra los signos sea una enfermera y
 * que el paciente exista antes de guardar el registro.
 */
@Service
public class CreateVitalSignsRecord {

    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private PatientPort patientPort;
    @Autowired
    private VitalSignsPort vitalSignsPort;

    public void create(VitalSignsRecord record) throws Exception {
        Employee nurse = employeePort.findByDocument(record.getNurse());
        if (nurse == null || !Role.NURSE.equals(nurse.getRole())) {
            throw new BusinessException("Los signos vitales solo pueden ser registrados por enfermeras");
        }
        Patient patient = patientPort.findByDocument(record.getPatient());
        if (patient == null) {
            throw new BusinessException("Los signos vitales deben asociarse a un paciente válido");
        }
        record.setNurse(nurse);
        record.setPatient(patient);
        record.setDateTime(new Timestamp(System.currentTimeMillis()));
        vitalSignsPort.save(record);
    }
}