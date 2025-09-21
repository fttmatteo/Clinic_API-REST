package app.domain.model;

import java.sql.Date;

public class ClinicalHistory {
    private int patientDocument;
    private Date attentionDate;
    private int doctorDocument;
    private String motive;
    private String symptoms;
    private String diagnosis;

    public int getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(int patientDocument) {
        this.patientDocument = patientDocument;
    }

    public Date getAttentionDate() {
        return attentionDate;
    }

    public void setAttentionDate(Date attentionDate) {
        this.attentionDate = attentionDate;
    }

    public int getDoctorDocument() {
        return doctorDocument;
    }

    public void setDoctorDocument(int doctorDocument) {
        this.doctorDocument = doctorDocument;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    }
