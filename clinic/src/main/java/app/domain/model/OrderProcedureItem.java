package app.domain.model;

public class OrderProcedureItem {
    private int numberOrder;
    private int item;
    private int idProcedure;
    private int quantity;
    private String frequency;
    private boolean specialistRequired;
    private int specialistTypeId;
    private long price;

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(int idProcedure) {
        this.idProcedure = idProcedure;
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

    public int getSpecialistTypeId() {
        return specialistTypeId;
    }

    public void setSpecialistTypeId(int specialistTypeId) {
        this.specialistTypeId = specialistTypeId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
