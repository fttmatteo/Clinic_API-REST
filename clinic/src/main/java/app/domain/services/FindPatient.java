package app.domain.services;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;

public class FindPatient {
    private PatientPort patientPort;

    public FindPatient(PatientPort patientPort) {
        this.patientPort = patientPort;
    }

    public Patient findByDocument(Patient patient) throws Exception {
        Patient found = patientPort.findByDocument(patient);
        if (found == null) {
            throw new Exception("No existe un paciente registrado con esa cédula");
        }
        return found;
    }
}
