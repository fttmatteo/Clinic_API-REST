package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.EmployeeEntity;

/**
 * Repositorio JPA para la entidad {@link EmployeeEntity}. Permite
 * consultar empleados por documento y nombre de usuario.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByDocument(Long document);
    EmployeeEntity findByUserName(String userName);
}