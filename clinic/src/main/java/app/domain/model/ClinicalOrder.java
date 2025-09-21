package app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
    private int numberOrder;
    private int patientDocument;
    private int doctorDocument;
    private Date dateCreation;
    public int getId() {
        return numberOrder;
    }

    public void setId(int id) {
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    }
