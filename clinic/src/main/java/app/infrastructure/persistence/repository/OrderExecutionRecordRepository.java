package app.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infrastructure.persistence.entities.OrderExecutionRecordEntity;
import app.infrastructure.persistence.entities.OrderItemEntity;

/**
 * Repositorio JPA para {@link OrderExecutionRecordEntity}. Permite
 * almacenar registros de administración de ítems de órdenes y buscarlos
 * por ítem.
 */
@Repository
public interface OrderExecutionRecordRepository extends JpaRepository<OrderExecutionRecordEntity, Long> {
    List<OrderExecutionRecordEntity> findByOrderItem(OrderItemEntity item);
}