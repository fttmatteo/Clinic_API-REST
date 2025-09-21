package app.adapter.in.rest.request;

public class OrderProcedureItemRequest {
    private String numberOrder;
    private String item;
    private String idProcedure;
    private String quantity;
    private String frequency;
    private String specialistRequired; // true/false
    private String specialistTypeId;   // conditional if required
    private String cost;

    public OrderProcedureItemRequest() {}

    public String getNumberOrder() { return numberOrder; }
    public void setNumberOrder(String numberOrder) { this.numberOrder = numberOrder; }
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    public String getIdProcedure() { return idProcedure; }
    public void setIdProcedure(String idProcedure) { this.idProcedure = idProcedure; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getSpecialistRequired() { return specialistRequired; }
    public void setSpecialistRequired(String specialistRequired) { this.specialistRequired = specialistRequired; }
    public String getSpecialistTypeId() { return specialistTypeId; }
    public void setSpecialistTypeId(String specialistTypeId) { this.specialistTypeId = specialistTypeId; }
    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }
}
