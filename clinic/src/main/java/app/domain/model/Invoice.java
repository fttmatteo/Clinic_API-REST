package app.domain.model;

import java.sql.Date;

public class Invoice {
    private long id;
    private Patient name;
    private Patient age;
    private Patient document;
    private Medic fullName;
    private Patient companyName;
    private Patient companyNumber;
    private int validityDays;
    private Patient validity;

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

    public Medic getFullName() {
        return fullName;
    }

    public void setFullName(Medic fullName) {
        this.fullName = fullName;
    }

    public Patient getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Patient companyName) {
        this.companyName = companyName;
    }

    public Patient getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Patient companyNumber) {
        this.companyNumber = companyNumber;
    }

    public int getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(int validityDays) {
        this.validityDays = validityDays;
    }

    public Patient getValidity() {
        return validity;
    }

    public void setValidity(Patient validity) {
        this.validity = validity;
    }
}
