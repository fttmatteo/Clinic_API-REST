package app.adapter.in.rest.request;

public class OrderMedicationItemRequest {
    private String numberOrder;
    private String item;
    private String idMedication;
    private String dose;
    private String treatmentDuration;
    private String cost;

    public OrderMedicationItemRequest() {}

    public String getNumberOrder() { return numberOrder; }
    public void setNumberOrder(String numberOrder) { this.numberOrder = numberOrder; }
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    public String getIdMedication() { return idMedication; }
    public void setIdMedication(String idMedication) { this.idMedication = idMedication; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
    public String getTreatmentDuration() { return treatmentDuration; }
    public void setTreatmentDuration(String treatmentDuration) { this.treatmentDuration = treatmentDuration; }
    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }
}
