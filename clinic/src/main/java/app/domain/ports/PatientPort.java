package app.domain.ports;

import app.domain.model.Patient;

public interface PatientPort {
    public Patient findByPatient(int documentPatient) throws Exception;
    public Patient save(Patient patient) throws Exception;
}
