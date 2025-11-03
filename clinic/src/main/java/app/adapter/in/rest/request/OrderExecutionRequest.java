package app.adapter.in.rest.request;

/**
 * Solicitud para registrar la administración de un ítem de una orden médica.
 * Incluye la identificación de la enfermera, la cantidad aplicada o
 * realizada y observaciones opcionales.
 */
public class OrderExecutionRequest {
    private String nurseDocument;
    private String amount;
    private String notes;

    public String getNurseDocument() {
        return nurseDocument;
    }
    public void setNurseDocument(String nurseDocument) {
        this.nurseDocument = nurseDocument;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}