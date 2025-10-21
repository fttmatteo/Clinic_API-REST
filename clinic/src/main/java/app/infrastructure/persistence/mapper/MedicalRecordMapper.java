package app.infrastructure.persistence.mapper;

import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.MedicalRecordEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Mapper para convertir entre {@link MedicalRecord} del dominio y
 * {@link MedicalRecordEntity} de la capa de persistencia. Gestiona la
 * conversión de las referencias a paciente, médico y orden médica.
 */
public class MedicalRecordMapper {
    public static MedicalRecordEntity toEntity(MedicalRecord record) {
        if (record == null) return null;
        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setId(record.getId());
        if (record.getPatient() != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(record.getPatient().getId());
            entity.setPatient(patientEntity);
        }
        if (record.getDoctor() != null) {
            EmployeeEntity doctorEntity = new EmployeeEntity();
            doctorEntity.setId(record.getDoctor().getId());
            entity.setDoctor(doctorEntity);
        }
        if (record.getMedicalOrder() != null) {
            MedicalOrderEntity orderEntity = new MedicalOrderEntity();
            orderEntity.setId(record.getMedicalOrder().getId());
            entity.setMedicalOrder(orderEntity);
        }
        entity.setDateTime(record.getDateTime());
        entity.setMotive(record.getMotive());
        entity.setSymptoms(record.getSymptoms());
        entity.setDiagnosis(record.getDiagnosis());
        entity.setActive(record.isActive());
        return entity;
    }

    public static MedicalRecord toDomain(MedicalRecordEntity entity) {
        if (entity == null) return null;
        MedicalRecord record = new MedicalRecord();
        record.setId(entity.getId());
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            record.setPatient(patient);
        }
        if (entity.getDoctor() != null) {
            Employee doctor = new Employee();
            doctor.setId(entity.getDoctor().getId());
            record.setDoctor(doctor);
        }
        if (entity.getMedicalOrder() != null) {
            MedicalOrder order = new MedicalOrder();
            order.setId(entity.getMedicalOrder().getId());
            record.setMedicalOrder(order);
        }
        record.setDateTime(entity.getDateTime());
        record.setMotive(entity.getMotive());
        record.setSymptoms(entity.getSymptoms());
        record.setDiagnosis(entity.getDiagnosis());
        record.setActive(entity.getActive());
        return record;
    }
}