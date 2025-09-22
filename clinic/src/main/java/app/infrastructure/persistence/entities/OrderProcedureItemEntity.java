package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_procedure_items")
public class OrderProcedureItemEntity {

  @EmbeddedId
  private OrderProcedureItemKey procedureItemId;

  @Column(nullable=false) private int procedureId;
  @Column(nullable=false) private int quantity;
  @Column(length=60) private String frequency;
  @Column(nullable=false) private boolean specialistRequired;
  @Column(nullable=false) private int specialistId; 
  @Column(nullable=false) private long price;

  public OrderProcedureItemKey getProcedureItemId() { return procedureItemId; }
  public void setProcedureItemId(OrderProcedureItemKey procedureItemId) { this.procedureItemId = procedureItemId; }
  public int getProcedureId() { return procedureId; }
  public void setProcedureId(int procedureId) { this.procedureId = procedureId; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public String getFrequency() { return frequency; }
  public void setFrequency(String frequency) { this.frequency = frequency; }
  public boolean isSpecialistRequired() { return specialistRequired; }
  public void setSpecialistRequired(boolean specialistRequired) { this.specialistRequired = specialistRequired; }
  public int getSpecialistId() { return specialistId; }
  public void setSpecialistId(int specialistId) { this.specialistId = specialistId; }
  public long getPrice() { return price; }
  public void setPrice(long price) { this.price = price; }
}
