package app.infrastructure.persistence.mapper;

import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.VitalSignsRecord;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.VitalSignsRecordEntity;

/**
 * Mapper para convertir entre {@link VitalSignsRecord} del dominio y
 * {@link VitalSignsRecordEntity} de la capa de persistencia. Maneja la
 * conversión de las referencias a enfermera y paciente.
 */
public class VitalSignsMapper {
    public static VitalSignsRecordEntity toEntity(VitalSignsRecord record) {
        if (record == null) return null;
        VitalSignsRecordEntity entity = new VitalSignsRecordEntity();
        entity.setId(record.getId());
        if (record.getNurse() != null) {
            EmployeeEntity nurse = new EmployeeEntity();
            nurse.setId(record.getNurse().getId());
            entity.setNurse(nurse);
        }
        if (record.getPatient() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setId(record.getPatient().getId());
            entity.setPatient(patient);
        }
        entity.setDateTime(record.getDateTime());
        entity.setBloodPressure(record.getBloodPressure());
        entity.setTemperature(record.getTemperature());
        entity.setPulse(record.getPulse());
        entity.setOxygenLevel(record.getOxygenLevel());
        return entity;
    }

    public static VitalSignsRecord toDomain(VitalSignsRecordEntity entity) {
        if (entity == null) return null;
        VitalSignsRecord record = new VitalSignsRecord();
        record.setId(entity.getId());
        if (entity.getNurse() != null) {
            Employee nurse = new Employee();
            nurse.setId(entity.getNurse().getId());
            record.setNurse(nurse);
        }
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            record.setPatient(patient);
        }
        record.setDateTime(entity.getDateTime());
        record.setBloodPressure(entity.getBloodPressure());
        record.setTemperature(entity.getTemperature());
        record.setPulse(entity.getPulse());
        record.setOxygenLevel(entity.getOxygenLevel());
        return record;
    }
}