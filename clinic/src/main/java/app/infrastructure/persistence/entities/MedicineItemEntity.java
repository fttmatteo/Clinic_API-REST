package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un ítem de tipo medicamento dentro de una
 * orden médica. Esta clase extiende de {@link OrderItemEntity} y añade
 * los campos específicos de la prescripción de medicamentos como la dosis
 * y la duración del tratamiento.
 */
@Entity
@Table(name = "order_medicines")
@PrimaryKeyJoinColumn(name = "id")
public class MedicineItemEntity extends OrderItemEntity {

    @Column
    private String dose;

    @Column
    private String treatmentDuration;

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