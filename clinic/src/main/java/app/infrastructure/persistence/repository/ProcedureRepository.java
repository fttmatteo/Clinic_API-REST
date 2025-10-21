package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.ProcedureEntity;

/**
 * Repositorio JPA para los procedimientos médicos.
 */
@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureEntity, String> {
}