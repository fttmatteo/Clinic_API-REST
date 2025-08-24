package app.domain.model;

public class ProcedureMedication {
    private int orderIdProcedure;
    private int idOrderProcedure;
    private int quantity;
    private String frequency;
    private boolean specialistRequired;
    private String specialistId;
    private String itemProcedure;

    public int getOrderIdProcedure() {
        return orderIdProcedure;
    }

    public void setOrderIdProcedure(int orderIdProcedure) {
        this.orderIdProcedure = orderIdProcedure;
    }

    public int getIdOrderProcedure() {
        return idOrderProcedure;
    }

    public void setIdOrderProcedure(int idOrderProcedure) {
        this.idOrderProcedure = idOrderProcedure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isSpecialistRequired() {
        return specialistRequired;
    }

    public void setSpecialistRequired(boolean specialistRequired) {
        this.specialistRequired = specialistRequired;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getItemProcedure() {
        return itemProcedure;
    }

    public void setItemProcedure(String itemProcedure) {
        this.itemProcedure = itemProcedure;
    }
}
