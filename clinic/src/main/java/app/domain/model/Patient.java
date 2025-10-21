package app.domain.model;

/**
 * Representa a un paciente de la clínica. Hereda los atributos comunes de
 * {@link Person} y añade información específica del paciente como el contacto de
 * emergencia y la póliza de seguro médico.
 */
public class Patient extends Person {
    private String firstNameEmergencyContact;
    private String lastNameEmergencyContact;
    private String relationShipEmergencyContact;
    private String phoneEmergencyContact;
    private InsurancePolicy insurancePolicy;

    public String getFirstNameEmergencyContact() {
        return firstNameEmergencyContact;
    }

    public void setFirstNameEmergencyContact(String firstNameEmergencyContact) {
        this.firstNameEmergencyContact = firstNameEmergencyContact;
    }

    public String getLastNameEmergencyContact() {
        return lastNameEmergencyContact;
    }

    public void setLastNameEmergencyContact(String lastNameEmergencyContact) {
        this.lastNameEmergencyContact = lastNameEmergencyContact;
    }

    public String getRelationShipEmergencyContact() {
        return relationShipEmergencyContact;
    }

    public void setRelationShipEmergencyContact(String relationShipEmergencyContact) {
        this.relationShipEmergencyContact = relationShipEmergencyContact;
    }

    public String getPhoneEmergencyContact() {
        return phoneEmergencyContact;
    }

    public void setPhoneEmergencyContact(String phoneEmergencyContact) {
        this.phoneEmergencyContact = phoneEmergencyContact;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}