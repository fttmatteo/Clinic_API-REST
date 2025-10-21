package app.adapter.in.rest.request;

/**
 * Solicitud para crear una factura. Contiene los identificadores del
 * paciente y del médico (opcional), el nombre del producto o servicio,
 * el monto, si se trata de un medicamento y, en ese caso, la orden
 * asociada.
 */
public class InvoiceRequest {
    private String patientId;
    private String doctorDocument;
    private String productName;
    private String productAmount;
    private String isMedicine;
    private String orderId;

    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getDoctorDocument() {
        return doctorDocument;
    }
    public void setDoctorDocument(String doctorDocument) {
        this.doctorDocument = doctorDocument;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductAmount() {
        return productAmount;
    }
    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }
    public String getIsMedicine() {
        return isMedicine;
    }
    public void setIsMedicine(String isMedicine) {
        this.isMedicine = isMedicine;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}