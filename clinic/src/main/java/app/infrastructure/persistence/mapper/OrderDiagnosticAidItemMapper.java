// OrderDiagnosticAidItemMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.OrderDiagnosticAidItem;
import app.infrastructure.persistence.entities.OrderDiagnosticAidItemEntity;
import app.infrastructure.persistence.entities.OrderDiagnosticAidItemKey;

@Component
public class OrderDiagnosticAidItemMapper {
  public OrderDiagnosticAidItem toDomain(OrderDiagnosticAidItemEntity e){
    if(e==null) return null;
    OrderDiagnosticAidItem d = new OrderDiagnosticAidItem();
    d.setNumberOrder(e.getDiagnosticId().getNumberOrder());
    d.setItem(e.getDiagnosticId().getItem());
    d.setDiagnosticAid(e.getDiagnosticAid());
    d.setQuantity(e.getQuantity());
    d.setSpecialistRequired(e.isSpecialistRequired());
    d.setSpecialistId(e.getSpecialistId());
    d.setPrice(e.getPrice());
    return d;
  }
  public OrderDiagnosticAidItemEntity toEntity(OrderDiagnosticAidItem d){
    OrderDiagnosticAidItemEntity e = new OrderDiagnosticAidItemEntity();
    e.setDiagnosticId(new OrderDiagnosticAidItemKey(d.getNumberOrder(), d.getItem()));
    e.setDiagnosticAid(d.getDiagnosticAid());
    e.setQuantity(d.getQuantity());
    e.setSpecialistRequired(d.isSpecialistRequired());
    e.setSpecialistId(d.getSpecialistId());
    e.setPrice(d.getPrice());
    return e;
  }
}
