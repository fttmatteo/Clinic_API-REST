package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.MedicineEntity;

/**
 * Repositorio JPA para los medicamentos.  Proporciona las operaciones
 * básicas de CRUD a través de {@link JpaRepository}.
 */
@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, String> {
}