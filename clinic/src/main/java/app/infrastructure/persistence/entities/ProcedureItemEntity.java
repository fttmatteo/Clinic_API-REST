package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un ítem de tipo procedimiento dentro de una
 * orden médica. Extiende de {@link OrderItemEntity} y añade los campos
 * específicos para procedimientos como la cantidad de veces que debe
 * realizarse y la frecuencia con la que se debe realizar.
 */
@Entity
@Table(name = "order_procedures")
@PrimaryKeyJoinColumn(name = "id")
public class ProcedureItemEntity extends OrderItemEntity {

    @Column
    private Integer quantity;

    @Column
    private String frequency;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}