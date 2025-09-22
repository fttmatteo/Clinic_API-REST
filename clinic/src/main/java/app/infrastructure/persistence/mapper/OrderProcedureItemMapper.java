// OrderProcedureItemMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.OrderProcedureItem;
import app.infrastructure.persistence.entities.OrderProcedureItemEntity;
import app.infrastructure.persistence.entities.OrderProcedureItemKey;

@Component
public class OrderProcedureItemMapper {
  public OrderProcedureItem toDomain(OrderProcedureItemEntity e){
    if(e==null) return null;
    OrderProcedureItem d = new OrderProcedureItem();
    d.setNumberOrder(e.getProcedureItemId().getNumberOrder());
    d.setItem(e.getProcedureItemId().getItem());
    d.setProcedureId(e.getProcedureId());
    d.setQuantity(e.getQuantity());
    d.setFrequency(e.getFrequency());
    d.setSpecialistRequired(e.isSpecialistRequired());
    d.setSpecialistId(e.getSpecialistId());
    d.setPrice(e.getPrice());
    return d;
  }
  public OrderProcedureItemEntity toEntity(OrderProcedureItem d){
    OrderProcedureItemEntity e = new OrderProcedureItemEntity();
    e.setProcedureItemId(new OrderProcedureItemKey(d.getNumberOrder(), d.getItem()));
    e.setProcedureId(d.getProcedureId());
    e.setQuantity(d.getQuantity());
    e.setFrequency(d.getFrequency());
    e.setSpecialistRequired(d.isSpecialistRequired());
    e.setSpecialistId(d.getSpecialistId());
    e.setPrice(d.getPrice());
    return e;
  }
}
