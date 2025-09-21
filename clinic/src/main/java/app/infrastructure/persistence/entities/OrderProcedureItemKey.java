package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderProcedureItemKey implements Serializable {
  private int numberOrder;
  private int item;

  public OrderProcedureItemKey() {}
  public OrderProcedureItemKey(int numberOrder, int item){ this.numberOrder=numberOrder; this.item=item; }
  public int getNumberOrder(){ return numberOrder; }
  public void setNumberOrder(int numberOrder){ this.numberOrder = numberOrder; }
  public int getItem(){ return item; }
  public void setItem(int item){ this.item = item; }

  @Override public boolean equals(Object o){
    if(this==o) return true;
    if(!(o instanceof OrderProcedureItemKey)) return false;
    OrderProcedureItemKey k=(OrderProcedureItemKey)o;
    return numberOrder==k.numberOrder && item==k.item;
  }
  @Override public int hashCode(){ return java.util.Objects.hash(numberOrder,item); }
}
