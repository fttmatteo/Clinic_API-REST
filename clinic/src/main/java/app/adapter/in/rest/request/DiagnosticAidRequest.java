package app.adapter.in.rest.request;

/**
 * Representa la solicitud de creación o actualización de una ayuda diagnóstica.
 */
public class DiagnosticAidRequest {
    private String id;
    private String name;
    private String cost;
    private String quantity;
    private String requiresSpecialist;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getRequiresSpecialist() {
        return requiresSpecialist;
    }
    public void setRequiresSpecialist(String requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }
}
