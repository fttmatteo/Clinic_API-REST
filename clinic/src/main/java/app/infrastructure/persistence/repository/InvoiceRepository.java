package app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.InvoiceEntity;

/**
 * Repositorio JPA para la entidad {@link InvoiceEntity}. Permite
 * almacenar y recuperar facturas de la base de datos.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query("SELECT COALESCE(SUM(i.copay), 0) FROM InvoiceEntity i WHERE i.patient.id = :patientId AND FUNCTION('YEAR', i.date) = :year")
    double sumCopayByPatientIdAndYear(@Param("patientId") Long patientId, @Param("year") int year);
    @Query("SELECT DISTINCT i FROM InvoiceEntity i "
         + "JOIN FETCH i.patient p "
         + "LEFT JOIN FETCH i.doctor d "
         + "LEFT JOIN FETCH i.order o "
         + "WHERE p.id = :patientId")
    List<InvoiceEntity> findDetailedByPatientId(@Param("patientId") Long patientId);
}
