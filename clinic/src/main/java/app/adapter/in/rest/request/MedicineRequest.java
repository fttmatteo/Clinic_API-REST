package app.adapter.in.rest.request;

/**
 * Representa la solicitud de creación o actualización de un medicamento.
 * Todos los campos se manejan como cadenas de texto para permitir
 * validación y conversión en los builders correspondientes.
 */
public class MedicineRequest {
    private String id;
    private String name;
    private String cost;
    private String dose;
    private String treatmentDuration;

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
}
