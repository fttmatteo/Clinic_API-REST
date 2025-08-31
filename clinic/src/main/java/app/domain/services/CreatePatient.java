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

    public Patient findByDocument(Patient patient) throws Exception {
        Patient found = patientPort.findByDocument(patient);
        if (found == null) {
            throw new Exception("No existe un paciente registrado con esa cédula");
        }
        return found;
    }
}
