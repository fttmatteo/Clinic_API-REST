package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.InvoiceEntity;

/**
 * Repositorio JPA para la entidad {@link InvoiceEntity}. Permite
 * almacenar y recuperar facturas de la base de datos.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}