package app.infrastructure.persistence.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Entidad JPA que representa a un paciente. Almacena información
 * personal y además incluye datos de contacto de emergencia y la póliza de
 * seguro asociada mediante una relación uno a uno. 
 */
@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true)
    private Long document;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 10)
    private String mobilePhone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String firstNameEmergencyContact;

    @Column(nullable = false, length = 50)
    private String lastNameEmergencyContact;

    @Column(nullable = false, length = 50)
    private String relationShipEmergencyContact;

    @Column(nullable = false, length = 10)
    private String phoneEmergencyContact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", nullable = false)
    private InsurancePolicyEntity insurancePolicy;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Long getDocument() {
        return document;
    }
    public void setDocument(Long document) {
        this.document = document;
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
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
    public InsurancePolicyEntity getInsurancePolicy() {
        return insurancePolicy;
    }
    public void setInsurancePolicy(InsurancePolicyEntity insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}