package app.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.MedicalOrder;
import app.domain.model.OrderItem;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.OrderItemEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Mapper para convertir entre {@link MedicalOrder} del dominio y
 * {@link MedicalOrderEntity} de la capa de persistencia. Gestiona la
 * conversión de las relaciones con paciente, médico y los ítems de
 * la orden.
 */
public class MedicalOrderMapper {
    public static MedicalOrderEntity toEntity(MedicalOrder order) {
        if (order == null) return null;
        MedicalOrderEntity entity = new MedicalOrderEntity();
        entity.setId(order.getId());
        entity.setOrderNumber(order.getOrderNumber());
        if (order.getPatient() != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(order.getPatient().getId());
            entity.setPatient(patientEntity);
        }
        if (order.getDoctor() != null) {
            EmployeeEntity doctorEntity = new EmployeeEntity();
            doctorEntity.setId(order.getDoctor().getId());
            entity.setDoctor(doctorEntity);
        }
        entity.setCreationDate(order.getCreationDate());
        if (order.getItems() != null) {
            List<OrderItemEntity> itemEntities = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemEntity itemEntity = OrderItemMapper.toEntity(item);
                itemEntity.setOrder(entity);
                itemEntities.add(itemEntity);
            }
            entity.setItems(itemEntities);
        }
        return entity;
    }

    public static MedicalOrder toDomain(MedicalOrderEntity entity) {
        if (entity == null) return null;
        MedicalOrder order = new MedicalOrder();
        order.setId(entity.getId());
        order.setOrderNumber(entity.getOrderNumber());
        if (entity.getPatient() != null) {
            order.setPatient(PatientMapper.toDomain(entity.getPatient()));
        }
        if (entity.getDoctor() != null) {
            order.setDoctor(EmployeeMapper.toDomain(entity.getDoctor()));
        }
        order.setCreationDate(entity.getCreationDate());
        if (entity.getItems() != null) {
            List<OrderItem> items = new ArrayList<>();
            for (OrderItemEntity itemEntity : entity.getItems()) {
                OrderItem item = OrderItemMapper.toDomain(itemEntity);
                items.add(item);
            }
            order.setItems(items);
        }
        return order;
    }
}
