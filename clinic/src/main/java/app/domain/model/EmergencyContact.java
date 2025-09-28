package app.domain.model;

/**
 * Representa el contacto de emergencia de un paciente. Incluye el nombre de
 * la persona de contacto, su relación con el paciente y un número de
 * teléfono. Sólo se permite un único contacto de emergencia por paciente.
 */
public class EmergencyContact {
    private long id;
    private String firstName;
    private String lastName;
    private String relationship;
    /** Número de teléfono de contacto de emergencia (10 dígitos). */
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}