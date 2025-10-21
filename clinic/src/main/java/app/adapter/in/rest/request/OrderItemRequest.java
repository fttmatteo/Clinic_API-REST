package app.adapter.in.rest.request;

/**
 * Representa los datos de un ítem dentro de una orden médica. Según el
 * tipo de ítem, algunos campos serán obligatorios y otros no. Todos los
 * campos son cadenas para facilitar la validación en la capa de
 * presentación.
 */
public class OrderItemRequest {
    private String itemNumber;
    private String type;
    private String name;
    private String dose;
    private String treatmentDuration;
    private String quantity;
    private String frequency;
    private String cost;
    private String requiresSpecialist;
    private String specialistTypeId;

    public String getItemNumber() {
        return itemNumber;
    }
    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
    }
    public String getTreatmentDuration() {
        return treatmentDuration;
    }
    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getRequiresSpecialist() {
        return requiresSpecialist;
    }
    public void setRequiresSpecialist(String requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }
    public String getSpecialistTypeId() {
        return specialistTypeId;
    }
    public void setSpecialistTypeId(String specialistTypeId) {
        this.specialistTypeId = specialistTypeId;
    }
}