package app.domain.model;

import java.sql.Date;

public class Patient{
    private long id;
    private String fullName;
    private int document;
    private int age;
    private Date birthDate;
    private String gender;
    private String address;
    private int phone;
    private String email;
    private String emergencyFirstName;
    private String emergencyLastName;
    private String relationShip;
    private int emergencyPhone;
    private MedicalInsurance companyName;
    private MedicalInsurance numberPolicy;
    private MedicalInsurance statusPolicy;
    private MedicalInsurance endDatePolicy;
    private User role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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

    public MedicalInsurance getCompanyName() {
        return companyName;
    }

    public void setCompanyName(MedicalInsurance companyName) {
        this.companyName = companyName;
    }

    public MedicalInsurance getNumberPolicy() {
        return numberPolicy;
    }

    public void setNumberPolicy(MedicalInsurance numberPolicy) {
        this.numberPolicy = numberPolicy;
    }

    public MedicalInsurance getStatusPolicy() {
        return statusPolicy;
    }

    public void setStatusPolicy(MedicalInsurance statusPolicy) {
        this.statusPolicy = statusPolicy;
    }

    public MedicalInsurance getEndDatePolicy() {
        return endDatePolicy;
    }

    public void setEndDatePolicy(MedicalInsurance endDatePolicy) {
        this.endDatePolicy = endDatePolicy;
    }

    public User getRole() {
        return role;
    }

    public void setRole(User role) {
        this.role = role;
    }
}
