package app.domain.ports;

import java.util.List;

import app.domain.model.MedicalOrder;
import app.domain.model.Patient;

/**
 * Puerto de persistencia para operaciones relacionadas con las órdenes
 * médicas. Permite almacenar y consultar órdenes asociadas a pacientes y
 * médicos.
 */
public interface MedicalOrderPort {
    void save(MedicalOrder order) throws Exception;
    MedicalOrder findById(MedicalOrder order) throws Exception;
    List<MedicalOrder> findByPatient(Patient patient) throws Exception;
}