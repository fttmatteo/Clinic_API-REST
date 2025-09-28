package app.adapter.in.rest.request;

/**
 * Representa la solicitud de creación de un paciente. Todos los campos se
 * manejan como cadenas de texto para que puedan ser validados y
 * convertidos por los builders correspondientes. Este objeto agrega
 * además los datos del contacto de emergencia y de la póliza de
 * seguro asociados al paciente.
 */
public class PatientRequest {
    private String fullName;
    private String document;
    private String birthDate;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String contactFirstName;
    private String contactLastName;
    private String contactRelation;
    private String contactPhone;
    private String companyName;
    private String policyNumber;
    private String policyStatus;
    private String policyExpiry;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactFirstName() {
        return contactFirstName;
    }
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }
    public String getContactLastName() {
        return contactLastName;
    }
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }
    public String getContactRelation() {
        return contactRelation;
    }
    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public String getPolicyStatus() {
        return policyStatus;
    }
    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }
    public String getPolicyExpiry() {
        return policyExpiry;
    }
    public void setPolicyExpiry(String policyExpiry) {
        this.policyExpiry = policyExpiry;
    }
}