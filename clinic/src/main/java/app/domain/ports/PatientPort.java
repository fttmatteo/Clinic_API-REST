package app.domain.ports;

import app.domain.model.Patient;

public interface PatientPort {
    public Patient findByDocument(int document) throws Exception;
    public Patient save(Patient patient) throws Exception;
}
