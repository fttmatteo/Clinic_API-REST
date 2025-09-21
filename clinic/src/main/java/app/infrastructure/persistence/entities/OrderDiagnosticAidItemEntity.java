package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_diagnostic_aid_items")
public class OrderDiagnosticAidItemEntity {

  @EmbeddedId
  private OrderDiagnosticAidItemKey id;

  @Column(nullable=false) private int idDiagnosticAid;
  @Column(nullable=false) private int quantity;
  @Column(nullable=false) private boolean specialistRequired;
  @Column(nullable=false) private int specialistTypeId; // 0 si no aplica
  @Column(nullable=false) private long cost;

  public OrderDiagnosticAidItemKey getId() { return id; }
  public void setId(OrderDiagnosticAidItemKey id) { this.id = id; }
  public int getIdDiagnosticAid() { return idDiagnosticAid; }
  public void setIdDiagnosticAid(int idDiagnosticAid) { this.idDiagnosticAid = idDiagnosticAid; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public boolean isSpecialistRequired() { return specialistRequired; }
  public void setSpecialistRequired(boolean specialistRequired) { this.specialistRequired = specialistRequired; }
  public int getSpecialistTypeId() { return specialistTypeId; }
  public void setSpecialistTypeId(int specialistTypeId) { this.specialistTypeId = specialistTypeId; }
  public long getCost() { return cost; }
  public void setCost(long cost) { this.cost = cost; }
}
