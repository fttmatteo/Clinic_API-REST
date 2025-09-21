package app.adapter.in.rest.request;

public class PatientRequest {
    private String document;
    private String fullName;
    private String birthDate;   // yyyy-MM-dd
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String emergencyFirstName;
    private String emergencyLastName;
    private String relationShip;
    private String emergencyPhone;

    public PatientRequest() {}

    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEmergencyFirstName() { return emergencyFirstName; }
    public void setEmergencyFirstName(String emergencyFirstName) { this.emergencyFirstName = emergencyFirstName; }
    public String getEmergencyLastName() { return emergencyLastName; }
    public void setEmergencyLastName(String emergencyLastName) { this.emergencyLastName = emergencyLastName; }
    public String getRelationShip() { return relationShip; }
    public void setRelationShip(String relationShip) { this.relationShip = relationShip; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }
}
