package app.domain.model;

public class OrderDiagnosticAidItem {
    private int numberOrder;
    private int item;
    private int idDiagnosticAid;
    private int quantity;
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

    public int getIdDiagnosticAid() {
        return idDiagnosticAid;
    }

    public void setIdDiagnosticAid(int idDiagnosticAid) {
        this.idDiagnosticAid = idDiagnosticAid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
