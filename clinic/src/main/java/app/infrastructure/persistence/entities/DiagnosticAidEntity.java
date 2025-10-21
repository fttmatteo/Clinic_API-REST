package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA para las ayudas diagnósticas. Se utiliza para mapear
 * objetos {@link app.domain.model.DiagnosticAid} a registros
 * en la base de datos.
 */
@Entity
@Table(name = "diagnostic_aids")
public class DiagnosticAidEntity {

    @Id
    @Column(nullable = false, length = 50)
    private String id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private Double cost;

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
}