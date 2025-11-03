package app.infrastructure.persistence.mapper;

import app.domain.model.Appointment;
import app.infrastructure.persistence.entities.AppointmentEntity;

/**
 * Mapper para convertir entre {@link Appointment} del dominio y
 * {@link AppointmentEntity} de la capa de persistencia.
 */
public class AppointmentMapper {
    public static AppointmentEntity toEntity(Appointment appointment) {
        if (appointment == null) return null;
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());
        entity.setPatient(PatientMapper.toEntity(appointment.getPatient()));
        entity.setDoctor(EmployeeMapper.toEntity(appointment.getDoctor()));
        entity.setDateTime(appointment.getDateTime());
        entity.setStatus(appointment.getStatus());
        return entity;
    }

    public static Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) return null;
        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());
        appointment.setPatient(PatientMapper.toDomain(entity.getPatient()));
        appointment.setDoctor(EmployeeMapper.toDomain(entity.getDoctor()));
        appointment.setDateTime(entity.getDateTime());
        appointment.setStatus(entity.getStatus());
        return appointment;
    }
}