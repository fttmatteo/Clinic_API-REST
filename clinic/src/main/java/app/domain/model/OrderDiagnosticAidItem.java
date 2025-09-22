package app.domain.model;

public class OrderDiagnosticAidItem {
    private int numberOrder;
    private int item;
    private int diagnosticAidId;
    private int quantity;
    private boolean specialistRequired;
    private int specialistId;
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

    public int getDiagnosticAid() {
        return diagnosticAidId;
    }

    public void setDiagnosticAid(int idDiagnosticAid) {
        this.diagnosticAidId = idDiagnosticAid;
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

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistTypeId) {
        this.specialistId = specialistTypeId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
