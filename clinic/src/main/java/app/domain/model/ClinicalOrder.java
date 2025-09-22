package app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
    private int numberOrder;
    private int patientDocument;
    private int doctorDocument;
    private Date creationDate;

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int id) {
        this.numberOrder = id;
    }

    public int getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(int patientDocument) {
        this.patientDocument = patientDocument;
    }

    public int getDoctorDocument() {
        return doctorDocument;
    }

    public void setDoctorDocument(int doctorDocument) {
        this.doctorDocument = doctorDocument;
    }

    public Date getCrationDate() {
        return creationDate;
    }

    public void setCreationDate(Date dateCreation) {
        this.creationDate = dateCreation;
    }
    }
