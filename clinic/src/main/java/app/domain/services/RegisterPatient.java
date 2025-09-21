package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Patient;
import app.domain.ports.PatientPort;

@Service
public class RegisterPatient {

    @Autowired
    private PatientPort patientPort;

    public Patient create(Patient patient) throws Exception {
        Patient existing = patientPort.findByPatient(patient.getDocument());
        if (existing != null) {
            throw new BusinessException("ya existe un paciente con esa cédula");
        }
        return patientPort.save(patient);
    }
}
