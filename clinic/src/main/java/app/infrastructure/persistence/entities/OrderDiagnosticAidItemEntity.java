package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_diagnostic_aid_items")
public class OrderDiagnosticAidItemEntity {

  @EmbeddedId
  private OrderDiagnosticAidItemKey diagnosticId;

  @Column(nullable=false) private int diagnosticAid;
  @Column(nullable=false) private int quantity;
  @Column(nullable=false) private boolean specialistRequired;
  @Column(nullable=false) private int specialistId;
  @Column(nullable=false) private long price;

  public OrderDiagnosticAidItemKey getDiagnosticId() { return diagnosticId; }
  public void setDiagnosticId(OrderDiagnosticAidItemKey diagnosticId) { this.diagnosticId = diagnosticId; }
  public int getDiagnosticAid() { return diagnosticAid; }
  public void setDiagnosticAid(int diagnosticAid) { this.diagnosticAid = diagnosticAid; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public boolean isSpecialistRequired() { return specialistRequired; }
  public void setSpecialistRequired(boolean specialistRequired) { this.specialistRequired = specialistRequired; }
  public int getSpecialistId() { return specialistId; }
  public void setSpecialistId(int specialistId) { this.specialistId = specialistId; }
  public long getPrice() { return price; }
  public void setPrice(long price) { this.price = price; }
}
