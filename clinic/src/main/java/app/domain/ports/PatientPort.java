package app.domain.ports;

import app.domain.model.Patient;

public interface PatientPort {
    Patient findByPatient(Integer documentPatient) throws Exception;
    Patient save(Patient patient) throws Exception;
}
