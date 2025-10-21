package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.VitalSignsRecordEntity;

/**
 * Repositorio JPA para la entidad {@link VitalSignsRecordEntity}. Este
 * repositorio ofrece las operaciones básicas de CRUD para los
 * registros de signos vitales.
 */
@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSignsRecordEntity, Long> {
}