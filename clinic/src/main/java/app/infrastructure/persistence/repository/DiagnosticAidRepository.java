package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.DiagnosticAidEntity;

/**
 * Repositorio JPA para las ayudas diagnósticas.
 */
@Repository
public interface DiagnosticAidRepository extends JpaRepository<DiagnosticAidEntity, String> {
}