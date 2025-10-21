package app.adapter.in.rest.request;

import java.util.List;

/**
 * Representa la solicitud para crear una orden médica. Incluye el
 * identificador de la orden, las referencias al médico y al paciente,
 * además de la lista de ítems que la componen. Los items se envían
 * como una lista de objetos {@link OrderItemRequest} que luego se
 * transformarán a dominios.
 */
public class MedicalOrderRequest {
    private String doctorDocument;
    private String patientId;
    private List<OrderItemRequest> items;

    public String getDoctorDocument() {
        return doctorDocument;
    }
    public void setDoctorDocument(String doctorDocument) {
        this.doctorDocument = doctorDocument;
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public List<OrderItemRequest> getItems() {
        return items;
    }
    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}