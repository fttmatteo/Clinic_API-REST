package app.infrastructure.persistence.mapper;

import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;
import app.infrastructure.persistence.entities.OrderItemEntity;
import app.infrastructure.persistence.entities.MedicineItemEntity;
import app.infrastructure.persistence.entities.ProcedureItemEntity;
import app.infrastructure.persistence.entities.DiagnosticAidItemEntity;

/**
 * Mapper para convertir entre {@link OrderItem} del dominio y
 * {@link OrderItemEntity} de la capa de persistencia. Se encarga de
 * trasladar los campos comunes y convertir el tipo entre enum y
 * cadena.
 */
public class OrderItemMapper {
    public static OrderItemEntity toEntity(OrderItem item) {
        if (item == null) return null;
        OrderItemEntity entity;
        if (item.getType() == OrderItemType.MEDICINE) {
            MedicineItemEntity medEntity = new MedicineItemEntity();
            medEntity.setDose(item.getDose());
            medEntity.setTreatmentDuration(item.getTreatmentDuration());
            entity = medEntity;
        } else if (item.getType() == OrderItemType.PROCEDURE) {
            ProcedureItemEntity procEntity = new ProcedureItemEntity();
            procEntity.setQuantity(item.getQuantity());
            procEntity.setFrequency(item.getFrequency());
            entity = procEntity;
        } else if (item.getType() == OrderItemType.DIAGNOSTIC_AID) {
            DiagnosticAidItemEntity aidEntity = new DiagnosticAidItemEntity();
            aidEntity.setQuantity(item.getQuantity());
            entity = aidEntity;
        } else {
            entity = new MedicineItemEntity();
        }
        entity.setId(item.getId());
        entity.setItemNumber(item.getItemNumber());
        if (item.getType() != null) {
            entity.setType(item.getType().name());
        }
        entity.setName(item.getName());
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
        item.setCost(entity.getCost());
        item.setRequiresSpecialist(entity.getRequiresSpecialist());
        item.setSpecialistTypeId(entity.getSpecialistTypeId());
        if (entity instanceof MedicineItemEntity) {
            MedicineItemEntity medEntity = (MedicineItemEntity) entity;
            item.setDose(medEntity.getDose());
            item.setTreatmentDuration(medEntity.getTreatmentDuration());
        }
        if (entity instanceof ProcedureItemEntity) {
            ProcedureItemEntity procEntity = (ProcedureItemEntity) entity;
            item.setQuantity(procEntity.getQuantity());
            item.setFrequency(procEntity.getFrequency());
        }
        if (entity instanceof DiagnosticAidItemEntity) {
            DiagnosticAidItemEntity aidEntity = (DiagnosticAidItemEntity) entity;
            item.setQuantity(aidEntity.getQuantity());
        }
        return item;
    }
}