package app.infrastructure.persistence.entities;

import jakarta.persistence.Embeddable;

/**
 * Representa el contacto de emergencia de un paciente. Se almacena
 * embebido dentro de la entidad de paciente y contiene los campos
 * necesarios para contactar a un familiar o persona allegada en caso de
 * urgencia.
 */
@Embeddable
public class EmergencyContactEmbeddable {
    private String firstName;
    private String lastName;
    private String relationship;
    private String phone;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}