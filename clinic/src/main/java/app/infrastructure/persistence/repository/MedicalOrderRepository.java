package app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Repositorio JPA para la entidad {@link MedicalOrderEntity}. Permite
 * consultar órdenes por paciente.
 */
@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {
    List<MedicalOrderEntity> findByPatient(PatientEntity patient);
}