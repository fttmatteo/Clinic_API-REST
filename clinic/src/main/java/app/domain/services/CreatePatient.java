package app.domain.services;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;

public class CreatePatient {
    private PatientPort patientPort;

    public void create(Patient patient) throws Exception {
        if (patient == null) {
            throw new Exception("El paciente es nulo");
        }
        patientPort.save(patient);
    }
}
