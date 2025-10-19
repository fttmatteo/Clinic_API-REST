package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmergencyContactValidator;
import app.adapter.in.validators.InsurancePolicyValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.InsurancePolicy;
import app.domain.model.Patient;

/**
 * Construye instancias de {@link Patient} a partir de cadenas de texto. Este
 * builder también crea el contacto de emergencia y la póliza de seguro
 * asociados al paciente. Todas las validaciones se delegan a sus
 * respectivos validadores.
 */
@Component
public class PatientBuilder {
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private EmergencyContactValidator contactValidator;
    @Autowired
    private InsurancePolicyValidator insuranceValidator;

    public Patient build(
            String fullName,
            String document,
            String birthDate,
            String gender,
            String address,
            String phone,
            String email,
            String contactFirstName,
            String contactLastName,
            String contactRelation,
            String contactPhone,
            String companyName,
            String policyNumber,
            String policyStatus,
            String policyExpiry
    ) throws Exception {
        Patient patient = new Patient();
        patient.setFullName(patientValidator.fullNameValidator(fullName));
        patient.setDocument(patientValidator.documentValidator(document));
        patient.setBirthDate(patientValidator.birthDateValidator(birthDate));
        patient.setGender(patientValidator.genderValidator(gender));
        patient.setAddress(patientValidator.addressValidator(address));
        patient.setPhone(patientValidator.phoneValidator(phone));
        patient.setEmail(patientValidator.emailValidator(email));
        patient.setFirstNameEmergencyContact(contactValidator.firstNameValidator(contactFirstName));
        patient.setLastNameEmergencyContact(contactValidator.lastNameValidator(contactLastName));
        patient.setRelationShipEmergencyContact(contactValidator.relationShipValidator(contactRelation));
        patient.setPhoneEmergencyContact(contactValidator.phoneValidator(contactPhone));
        InsurancePolicy policy = new InsurancePolicy();
        policy.setCompanyName(insuranceValidator.companyNameValidator(companyName));
        policy.setPolicyNumber(insuranceValidator.policyNumberValidator(policyNumber));
        policy.setActive(insuranceValidator.activeValidator(policyStatus));
        policy.setExpiryDate(insuranceValidator.expiryDateValidator(policyExpiry));
        patient.setInsurancePolicy(policy);
        return patient;
    }
}