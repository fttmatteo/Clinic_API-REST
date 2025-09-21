package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDiagnosticAidItemKey implements Serializable {
  private int numberOrder;
  private int item;

  public OrderDiagnosticAidItemKey() {}
  public OrderDiagnosticAidItemKey(int numberOrder, int item){ this.numberOrder=numberOrder; this.item=item; }
  public int getNumberOrder(){ return numberOrder; }
  public void setNumberOrder(int numberOrder){ this.numberOrder = numberOrder; }
  public int getItem(){ return item; }
  public void setItem(int item){ this.item = item; }

  @Override public boolean equals(Object o){
    if(this==o) return true;
    if(!(o instanceof OrderDiagnosticAidItemKey)) return false;
    OrderDiagnosticAidItemKey k=(OrderDiagnosticAidItemKey)o;
    return numberOrder==k.numberOrder && item==k.item;
  }
  @Override public int hashCode(){ return Objects.hash(numberOrder,item); }
}
