package app.infrastructure.persistence.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Repositorio JPA para la entidad {@link AppointmentEntity}. Proporciona
 * métodos de búsqueda personalizados por médico, paciente y fecha.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByDoctorAndDateTime(EmployeeEntity doctor, Timestamp dateTime);
    List<AppointmentEntity> findByPatient(PatientEntity patient);
    List<AppointmentEntity> findByDoctor(EmployeeEntity doctor);
}