package app.domain.services;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;

public class EditPatient {
    private PatientPort patientPort;

    public EditPatient(PatientPort patientPort) {
        this.patientPort = patientPort;
    }

    public void edit(Patient patient) throws Exception {
        if (patient == null) {
            throw new Exception("El paciente es nulo");
        }
        patientPort.save(patient);
    }
}
