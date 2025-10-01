package app.infrastructure.persistence.mapper;

import app.domain.model.InsurancePolicy;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;
import app.infrastructure.persistence.entities.InsurancePolicyEmbeddable;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Mapper para convertir entre {@link Patient} del dominio y
 * {@link PatientEntity} de la capa de persistencia.
 */
public class PatientMapper {
    public static PatientEntity toEntity(Patient patient) {
        if (patient == null) return null;
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setFullName(patient.getFullName());
        entity.setDocument(patient.getDocument());
        entity.setBirthDate(patient.getBirthDate());
        if (patient.getGender() != null) {
            entity.setGender(patient.getGender().name());
        }
        entity.setAddress(patient.getAddress());
        entity.setMobilePhone(patient.getPhone());
        entity.setEmail(patient.getEmail());
        entity.setFirstNameEmergencyContact(patient.getFirstNameEmergencyContact());
        entity.setLastNameEmergencyContact(patient.getLastNameEmergencyContact());
        entity.setRelationShipEmergencyContact(patient.getRelationShipEmergencyContact());
        entity.setPhoneEmergencyContact(patient.getPhoneEmergencyContact());
        if (patient.getInsurancePolicy() != null) {
            InsurancePolicyEmbeddable policy = new InsurancePolicyEmbeddable();
            policy.setCompanyName(patient.getInsurancePolicy().getCompanyName());
            policy.setPolicyNumber(patient.getInsurancePolicy().getPolicyNumber());
            policy.setActive(patient.getInsurancePolicy().isActive());
            policy.setExpiryDate(patient.getInsurancePolicy().getExpiryDate());
            entity.setInsurancePolicy(policy);
        }
        return entity;
    }

    public static Patient toDomain(PatientEntity entity) {
        if (entity == null) return null;
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setFullName(entity.getFullName());
        patient.setDocument(entity.getDocument());
        patient.setBirthDate(entity.getBirthDate());
        if (entity.getGender() != null) {
            patient.setGender(Gender.valueOf(entity.getGender()));
        }
        patient.setAddress(entity.getAddress());
        patient.setPhone(entity.getMobilePhone());
        patient.setEmail(entity.getEmail());
        patient.setFirstNameEmergencyContact(entity.getFirstNameEmergencyContact());
        patient.setLastNameEmergencyContact(entity.getLastNameEmergencyContact());
        patient.setRelationShipEmergencyContact(entity.getRelationShipEmergencyContact());
        patient.setPhoneEmergencyContact(entity.getPhoneEmergencyContact());
        if (entity.getInsurancePolicy() != null) {
            InsurancePolicy policy = new InsurancePolicy();
            policy.setCompanyName(entity.getInsurancePolicy().getCompanyName());
            policy.setPolicyNumber(entity.getInsurancePolicy().getPolicyNumber());
            policy.setActive(entity.getInsurancePolicy().isActive());
            policy.setExpiryDate(entity.getInsurancePolicy().getExpiryDate());
            patient.setInsurancePolicy(policy);
        }
        return patient;
    }
}