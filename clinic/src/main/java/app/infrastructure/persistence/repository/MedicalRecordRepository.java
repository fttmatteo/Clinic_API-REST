package app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.MedicalRecordEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Repositorio JPA para la entidad {@link MedicalRecordEntity}. Permite
 * consultar las historias clínicas de un paciente.
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
    List<MedicalRecordEntity> findByPatient(PatientEntity patient);
}