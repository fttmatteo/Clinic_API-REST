package app.domain.ports;

import java.util.List;

import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

/**
 * Puerto de persistencia para las historias clínicas. Permite guardar un
 * registro médico y consultar todos los registros asociados a un paciente.
 */
public interface MedicalRecordPort {
    void save(MedicalRecord record) throws Exception;
    List<MedicalRecord> findByPatient(Patient patient) throws Exception;
}