package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un ítem de tipo ayuda diagnóstica dentro de
 * una orden médica. Extiende de {@link OrderItemEntity} y añade el
 * campo específico de cantidad de ayudas diagnósticas a realizar.
 */
@Entity
@Table(name = "order_diagnostic_aids")
@PrimaryKeyJoinColumn(name = "id")
public class DiagnosticAidItemEntity extends OrderItemEntity {

    @Column
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}