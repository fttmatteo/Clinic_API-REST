// OrderMedicationItemMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.OrderMedicationItem;
import app.infrastructure.persistence.entities.OrderMedicationItemEntity;
import app.infrastructure.persistence.entities.OrderMedicationItemKey;

@Component
public class OrderMedicationItemMapper {
  public OrderMedicationItem toDomain(OrderMedicationItemEntity e){
    if(e==null) return null;
    OrderMedicationItem d = new OrderMedicationItem();
    d.setNumberOrder(e.getMedicationId().getNumberOrder());
    d.setItem(e.getMedicationId().getItem());
    d.setMedicineId(e.getMedicineId());
    d.setDose(e.getDose());
    d.setTreatmentDuration(e.getTreatmentDuration());
    d.setPrice(e.getPrice());
    return d;
  }
  public OrderMedicationItemEntity toEntity(OrderMedicationItem d){
    OrderMedicationItemEntity e = new OrderMedicationItemEntity();
    e.setMedicationId(new OrderMedicationItemKey(d.getNumberOrder(), d.getItem()));
    e.setMedicineId(d.getMedicineId());
    e.setDose(d.getDose());
    e.setTreatmentDuration(d.getTreatmentDuration());
    e.setPrice(d.getPrice());
    return e;
  }
}
