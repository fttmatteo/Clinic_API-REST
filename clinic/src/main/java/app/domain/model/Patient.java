package app.domain.model;

/**
 * Representa a un paciente de la clínica. Hereda los atributos comunes de
 * {@link Person} y añade información específica del paciente como el contacto de
 * emergencia y la póliza de seguro médico.
 */
public class Patient extends Person {
    private EmergencyContact emergencyContact;
    private InsurancePolicy insurancePolicy;

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}