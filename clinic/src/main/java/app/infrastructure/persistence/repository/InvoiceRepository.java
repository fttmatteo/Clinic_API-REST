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
    /**
     * Calcula la suma de los copagos para un paciente en un año específico.
     * Si no existen facturas para ese paciente y año, retorna 0.
     *
     * @param patientId identificador del paciente
     * @param year      año calendario (por ejemplo 2025)
     * @return la suma de los valores de copago en el año indicado
     */
    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(SUM(i.copay), 0) FROM InvoiceEntity i WHERE i.patient.id = :patientId AND FUNCTION('YEAR', i.date) = :year")
    double sumCopayByPatientIdAndYear(@org.springframework.data.repository.query.Param("patientId") Long patientId,
                                      @org.springframework.data.repository.query.Param("year") int year);
}