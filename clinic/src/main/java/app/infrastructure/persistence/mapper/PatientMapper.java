package app.infrastructure.persistence.mapper;

import app.domain.model.EmergencyContact;
import app.domain.model.InsurancePolicy;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;
import app.infrastructure.persistence.entities.EmergencyContactEmbeddable;
import app.infrastructure.persistence.entities.InsurancePolicyEmbeddable;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Mapper para convertir entre {@link Patient} del dominio y
 * {@link PatientEntity} de la capa de persistencia. Maneja la
 * conversión de los componentes embebidos de contacto de emergencia
 * y póliza de seguro.
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
        if (patient.getEmergencyContact() != null) {
            EmergencyContactEmbeddable contact = new EmergencyContactEmbeddable();
            contact.setFirstName(patient.getEmergencyContact().getFirstName());
            contact.setLastName(patient.getEmergencyContact().getLastName());
            contact.setRelationship(patient.getEmergencyContact().getRelationship());
            contact.setPhone(patient.getEmergencyContact().getPhone());
            entity.setEmergencyContact(contact);
        }
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
        if (entity.getEmergencyContact() != null) {
            EmergencyContact contact = new EmergencyContact();
            contact.setFirstName(entity.getEmergencyContact().getFirstName());
            contact.setLastName(entity.getEmergencyContact().getLastName());
            contact.setRelationship(entity.getEmergencyContact().getRelationship());
            contact.setPhone(entity.getEmergencyContact().getPhone());
            patient.setEmergencyContact(contact);
        }
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