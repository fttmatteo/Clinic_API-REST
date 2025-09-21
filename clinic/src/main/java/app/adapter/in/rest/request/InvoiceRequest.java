package app.adapter.in.rest.request;

public class InvoiceRequest {
    private String patientDocument;
    private String professionalName;
    private String clinicalDetail;
    private String totalService;   // long
    private String invoiceDate;    // yyyy-MM-dd

    public InvoiceRequest() {}

    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public String getProfessionalName() { return professionalName; }
    public void setProfessionalName(String professionalName) { this.professionalName = professionalName; }
    public String getClinicalDetail() { return clinicalDetail; }
    public void setClinicalDetail(String clinicalDetail) { this.clinicalDetail = clinicalDetail; }
    public String getTotalService() { return totalService; }
    public void setTotalService(String totalService) { this.totalService = totalService; }
    public String getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(String invoiceDate) { this.invoiceDate = invoiceDate; }
}
