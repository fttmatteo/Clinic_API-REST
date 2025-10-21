package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Repositorio JPA para la entidad {@link PatientEntity}. Permite
 * consultar pacientes por documento y por nombre de usuario además de
 * acceder a las operaciones estándar de CRUD.
 */
@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findByDocument(Long document);
}