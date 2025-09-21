package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_procedure_items")
public class OrderProcedureItemEntity {

  @EmbeddedId
  private OrderProcedureItemKey id;

  @Column(nullable=false) private int idProcedure;
  @Column(nullable=false) private int quantity;
  @Column(length=60) private String frequency;
  @Column(nullable=false) private boolean specialistRequired;
  @Column(nullable=false) private int specialistTypeId; // 0 si no aplica
  @Column(nullable=false) private long cost;

  public OrderProcedureItemKey getId() { return id; }
  public void setId(OrderProcedureItemKey id) { this.id = id; }
  public int getIdProcedure() { return idProcedure; }
  public void setIdProcedure(int idProcedure) { this.idProcedure = idProcedure; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public String getFrequency() { return frequency; }
  public void setFrequency(String frequency) { this.frequency = frequency; }
  public boolean isSpecialistRequired() { return specialistRequired; }
  public void setSpecialistRequired(boolean specialistRequired) { this.specialistRequired = specialistRequired; }
  public int getSpecialistTypeId() { return specialistTypeId; }
  public void setSpecialistTypeId(int specialistTypeId) { this.specialistTypeId = specialistTypeId; }
  public long getCost() { return cost; }
  public void setCost(long cost) { this.cost = cost; }
}
