package app.domain.model;

import java.sql.Date;

public class Patient {
    private int document;
    private String fullName;
    private Date birthDate;
    private String gender;
    private String address;
    private int phoneNumber;
    private String email;   
    private String emergencyFirstName;
    private String emergencyLastName;
    private String relationShip;
    private int emergencyPhone;
    private MedicalInsurance insurancePolicy; //Preguntale al profesor

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyFirstName() {
        return emergencyFirstName;
    }

    public void setEmergencyFirstName(String emergencyFirstName) {
        this.emergencyFirstName = emergencyFirstName;
    }

    public String getEmergencyLastName() {
        return emergencyLastName;
    }

    public void setEmergencyLastName(String emergencyLastName) {
        this.emergencyLastName = emergencyLastName;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public int getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(int emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public MedicalInsurance getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(MedicalInsurance insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}