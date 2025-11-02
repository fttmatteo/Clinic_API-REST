package app.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.OrderExecutionRecord;
import app.domain.model.OrderItem;
import app.domain.ports.OrderExecutionRecordPort;
import app.infrastructure.persistence.mapper.OrderExecutionRecordMapper;
import app.infrastructure.persistence.mapper.OrderItemMapper;
import app.infrastructure.persistence.repository.OrderExecutionRecordRepository;

/**
 * Adaptador de infraestructura que implementa el puerto de persistencia para
 * los registros de ejecución de ítems de órdenes. Convierte entre objetos
 * de dominio y entidades JPA mediante los mapeadores y delega las
 * operaciones en el repositorio de Spring Data JPA.
 */
@Service
public class OrderExecutionRecordAdapter implements OrderExecutionRecordPort {

    @Autowired
    private OrderExecutionRecordRepository recordRepository;

    @Override
    public void save(OrderExecutionRecord record) throws Exception {
        var entity = OrderExecutionRecordMapper.toEntity(record);
        recordRepository.save(entity);
        record.setId(entity.getId());
    }

    @Override
    public List<OrderExecutionRecord> findByOrderItem(OrderItem item) throws Exception {
        var entities = recordRepository.findByOrderItem(OrderItemMapper.toEntity(item));
        return entities.stream().map(OrderExecutionRecordMapper::toDomain).collect(Collectors.toList());
    }
}