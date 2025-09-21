package app.adapter.in.rest.request;

public class ClinicalOrderRequest {
    private String numberOrder;
    private String patientDocument;
    private String professionalDocument;
    private String creationDate;   // yyyy-MM-dd

    public ClinicalOrderRequest() {}

    public String getNumberOrder() { return numberOrder; }
    public void setNumberOrder(String numberOrder) { this.numberOrder = numberOrder; }
    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public String getProfessionalDocument() { return professionalDocument; }
    public void setProfessionalDocument(String professionalDocument) { this.professionalDocument = professionalDocument; }
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
}
