package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_medication_items")
public class OrderMedicationItemEntity {

  @EmbeddedId
  private OrderMedicationItemKey id;

  @Column(nullable=false) private int idMedication;
  @Column(nullable=false) private int dose;
  @Column(length=80) private String treatmentDuration;
  @Column(nullable=false) private long cost;

  public OrderMedicationItemKey getId() { return id; }
  public void setId(OrderMedicationItemKey id) { this.id = id; }
  public int getIdMedication() { return idMedication; }
  public void setIdMedication(int idMedication) { this.idMedication = idMedication; }
  public int getDose() { return dose; }
  public void setDose(int dose) { this.dose = dose; }
  public String getTreatmentDuration() { return treatmentDuration; }
  public void setTreatmentDuration(String treatmentDuration) { this.treatmentDuration = treatmentDuration; }
  public long getCost() { return cost; }
  public void setCost(long cost) { this.cost = cost; }
}
