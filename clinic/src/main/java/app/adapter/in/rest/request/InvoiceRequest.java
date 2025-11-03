package app.adapter.in.rest.request;

/**
 * Solicitud para crear una factura. Contiene el documento del paciente,
 * el documento del médico (opcional) y el número de la orden clínica
 * asociada que respalda la facturación.
 */
public class InvoiceRequest {
    private String patientId;
    private String doctorDocument;
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
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
