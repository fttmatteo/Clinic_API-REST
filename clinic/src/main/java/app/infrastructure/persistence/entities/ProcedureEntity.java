package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un procedimiento médico.  Esta entidad
 * corresponde con el modelo de dominio {@link app.domain.model.Procedure}.
 */
@Entity
@Table(name = "procedures")
public class ProcedureEntity {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "default_quantity")
    private Integer defaultQuantity;

    @Column(name = "default_frequency", length = 100)
    private String defaultFrequency;

    @Column(name = "default_requires_specialist")
    private Boolean defaultRequiresSpecialist;

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

    public Integer getDefaultQuantity() {
        return defaultQuantity;
    }

    public void setDefaultQuantity(Integer defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

    public String getDefaultFrequency() {
        return defaultFrequency;
    }

    public void setDefaultFrequency(String defaultFrequency) {
        this.defaultFrequency = defaultFrequency;
    }

    public Boolean getDefaultRequiresSpecialist() {
        return defaultRequiresSpecialist;
    }

    public void setDefaultRequiresSpecialist(Boolean defaultRequiresSpecialist) {
        this.defaultRequiresSpecialist = defaultRequiresSpecialist;
    }
}
