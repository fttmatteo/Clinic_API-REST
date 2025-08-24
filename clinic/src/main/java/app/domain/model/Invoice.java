package app.domain.model;

import java.sql.Date;

public class Invoice {
    private long id;
    private Patient name;
    private Patient age;
    private Patient document;
    private Employee fullName;
    private MedicalInsurance companyName;
    private MedicalInsurance numberPolicy;
    private MedicalInsurance validityDaysPolicy;
    private MedicalInsurance endDatePolicy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getName() {
        return name;
    }

    public void setName(Patient name) {
        this.name = name;
    }

    public Patient getAge() {
        return age;
    }

    public void setAge(Patient age) {
        this.age = age;
    }

    public Patient getDocument() {
        return document;
    }

    public void setDocument(Patient document) {
        this.document = document;
    }

    public Employee getFullName() {
        return fullName;
    }

    public void setFullName(Employee fullName) {
        this.fullName = fullName;
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

    public MedicalInsurance getValidityDaysPolicy() {
        return validityDaysPolicy;
    }

    public void setValidityDaysPolicy(MedicalInsurance validityDaysPolicy) {
        this.validityDaysPolicy = validityDaysPolicy;
    }

    public MedicalInsurance getEndDatePolicy() {
        return endDatePolicy;
    }

    public void setEndDatePolicy(MedicalInsurance endDatePolicy) {
        this.endDatePolicy = endDatePolicy;
    }
}
