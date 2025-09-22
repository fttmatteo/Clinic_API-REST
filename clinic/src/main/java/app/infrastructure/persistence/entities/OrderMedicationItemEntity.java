package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_medication_items")
public class OrderMedicationItemEntity {

  @EmbeddedId
  private OrderMedicationItemKey medicationId;

  @Column(nullable=false) private int medicineId;
  @Column(nullable=false) private int dose;
  @Column(length=80) private String treatmentDuration;
  @Column(nullable=false) private long price;

  public OrderMedicationItemKey getMedicationId() { return medicationId; }
  public void setMedicationId(OrderMedicationItemKey medicationId) { this.medicationId = medicationId; }
  public int getMedicineId() { return medicineId; }
  public void setMedicineId(int medicineId) { this.medicineId = medicineId; }
  public int getDose() { return dose; }
  public void setDose(int dose) { this.dose = dose; }
  public String getTreatmentDuration() { return treatmentDuration; }
  public void setTreatmentDuration(String treatmentDuration) { this.treatmentDuration = treatmentDuration; }
  public long getPrice() { return price; }
  public void setPrice(long price) { this.price = price; }
}
