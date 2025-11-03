package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un medicamento en la base de datos.  Se
 * corresponde con la clase de dominio {@link app.domain.model.Medicine}.
 */
@Entity
@Table(name = "medicines")
public class MedicineEntity {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "default_dose", length = 100)
    private String defaultDose;

    @Column(name = "default_treatment_duration", length = 100)
    private String defaultTreatmentDuration;

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDefaultDose() {
        return defaultDose;
    }

    public void setDefaultDose(String defaultDose) {
        this.defaultDose = defaultDose;
    }

    public String getDefaultTreatmentDuration() {
        return defaultTreatmentDuration;
    }

    public void setDefaultTreatmentDuration(String defaultTreatmentDuration) {
        this.defaultTreatmentDuration = defaultTreatmentDuration;
    }
}
