package app.domain.model;

import java.sql.Date;

public class ClinicalHistory {
    private Integer patientDocument;
    private Date attentionDate;
    private Integer doctorDocument;
    private String motive;
    private String symptoms;
    private String diagnosis;

    public Integer getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(Integer patientDocument) {
        this.patientDocument = patientDocument;
    }

    public Date getAttentionDate() {
        return attentionDate;
    }

    public void setAttentionDate(Date attentionDate) {
        this.attentionDate = attentionDate;
    }

    public Integer getDoctorDocument() {
        return doctorDocument;
    }

    public void setDoctorDocument(Integer doctorDocument) {
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
