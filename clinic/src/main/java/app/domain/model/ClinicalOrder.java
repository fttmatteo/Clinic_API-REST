package app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
    private Date date;
    private Medic document;
    private String consultationMotive;
    private String symptomatology;
    private String diagnostic;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Medic getDocument() {
        return document;
    }

    public void setDocument(Medic document) {
        this.document = document;
    }

    public String getConsultationMotive() {
        return consultationMotive;
    }

    public void setConsultationMotive(String consultationMotive) {
        this.consultationMotive = consultationMotive;
    }

    public String getSymptomatology() {
        return symptomatology;
    }

    public void setSymptomatology(String symptomatology) {
        this.symptomatology = symptomatology;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
