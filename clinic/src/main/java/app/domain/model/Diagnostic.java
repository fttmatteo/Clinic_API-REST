package app.domain.model;

public class Diagnostic {
    private int NumberOrder;
    private int idDiagnostic;
    private long quantity;
    private boolean specialistRequired;
    private String specialtyId;
    private String itemId;

    public int getNumberOrder() {
        return NumberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        NumberOrder = numberOrder;
    }

    public int getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(int idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isSpecialistRequired() {
        return specialistRequired;
    }

    public void setSpecialistRequired(boolean specialistRequired) {
        this.specialistRequired = specialistRequired;
    }

    public String getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
