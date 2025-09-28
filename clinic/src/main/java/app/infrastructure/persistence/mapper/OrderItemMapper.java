package app.infrastructure.persistence.mapper;

import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;
import app.infrastructure.persistence.entities.OrderItemEntity;

/**
 * Mapper para convertir entre {@link OrderItem} del dominio y
 * {@link OrderItemEntity} de la capa de persistencia. Se encarga de
 * trasladar los campos comunes y convertir el tipo entre enum y
 * cadena.
 */
public class OrderItemMapper {
    public static OrderItemEntity toEntity(OrderItem item) {
        if (item == null) return null;
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(item.getId());
        entity.setItemNumber(item.getItemNumber());
        if (item.getType() != null) {
            entity.setType(item.getType().name());
        }
        entity.setName(item.getName());
        entity.setDose(item.getDose());
        entity.setTreatmentDuration(item.getTreatmentDuration());
        entity.setQuantity(item.getQuantity());
        entity.setFrequency(item.getFrequency());
        entity.setCost(item.getCost());
        entity.setRequiresSpecialist(item.getRequiresSpecialist());
        entity.setSpecialistTypeId(item.getSpecialistTypeId());
        return entity;
    }

    public static OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;
        OrderItem item = new OrderItem();
        item.setId(entity.getId());
        item.setItemNumber(entity.getItemNumber());
        if (entity.getType() != null) {
            item.setType(OrderItemType.valueOf(entity.getType()));
        }
        item.setName(entity.getName());
        item.setDose(entity.getDose());
        item.setTreatmentDuration(entity.getTreatmentDuration());
        item.setQuantity(entity.getQuantity());
        item.setFrequency(entity.getFrequency());
        item.setCost(entity.getCost());
        item.setRequiresSpecialist(entity.getRequiresSpecialist());
        item.setSpecialistTypeId(entity.getSpecialistTypeId());
        return item;
    }
}