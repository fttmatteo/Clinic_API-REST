package app.infrastructure.persistence.mapper;

import app.domain.model.OrderExecutionRecord;
import app.infrastructure.persistence.entities.OrderExecutionRecordEntity;

/**
 * Mapper para convertir entre {@link OrderExecutionRecord} del dominio y
 * {@link OrderExecutionRecordEntity} de la capa de persistencia.
 */
public class OrderExecutionRecordMapper {
    public static OrderExecutionRecordEntity toEntity(OrderExecutionRecord record) {
        if (record == null) return null;
        OrderExecutionRecordEntity entity = new OrderExecutionRecordEntity();
        entity.setId(record.getId());
        entity.setOrderItem(OrderItemMapper.toEntity(record.getOrderItem()));
        entity.setNurse(EmployeeMapper.toEntity(record.getNurse()));
        entity.setDateTime(record.getDateTime());
        entity.setAmount(record.getAmount());
        entity.setNotes(record.getNotes());
        return entity;
    }

    public static OrderExecutionRecord toDomain(OrderExecutionRecordEntity entity) {
        if (entity == null) return null;
        OrderExecutionRecord record = new OrderExecutionRecord();
        record.setId(entity.getId());
        record.setOrderItem(OrderItemMapper.toDomain(entity.getOrderItem()));
        record.setNurse(EmployeeMapper.toDomain(entity.getNurse()));
        record.setDateTime(entity.getDateTime());
        record.setAmount(entity.getAmount());
        record.setNotes(entity.getNotes());
        return record;
    }
}