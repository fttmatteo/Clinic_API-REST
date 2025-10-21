package app.domain.ports;

import app.domain.model.Patient;

/**
 * Puerto de persistencia para operaciones relacionadas con pacientes. Permite
 * consultar y almacenar pacientes en la base de datos sin acoplarse a la
 * tecnología de persistencia.
 */
public interface PatientPort {
    Patient findByDocument(Patient patient) throws Exception;
    Patient findById(Patient patient) throws Exception;
    void save(Patient patient) throws Exception;
}