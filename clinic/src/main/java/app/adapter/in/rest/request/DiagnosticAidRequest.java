package app.adapter.in.rest.request;

/**
 * Representa la solicitud de creación o actualización de una ayuda diagnóstica.
 */
public class DiagnosticAidRequest {
    private String id;
    private String name;
    private String cost;

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
}