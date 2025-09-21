package app.adapter.in.rest.request;

public class ClinicalHistoryRequest {
    private String patientDocument;
    private String date;                 // yyyy-MM-dd
    private String professionalDocument;
    private String motive;
    private String symptoms;
    private String diagnosis;

    public ClinicalHistoryRequest() {}

    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getProfessionalDocument() { return professionalDocument; }
    public void setProfessionalDocument(String professionalDocument) { this.professionalDocument = professionalDocument; }
    public String getMotive() { return motive; }
    public void setMotive(String motive) { this.motive = motive; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
