package app.domain.ports;

import java.util.List;

import app.domain.model.OrderExecutionRecord;
import app.domain.model.OrderItem;

/**
 * Puerto de persistencia para los registros de aplicación de ítems de órdenes.
 * Permite almacenar y consultar registros de medicamentos administrados o
 * procedimientos realizados. No se prevé la eliminación de estos registros,
 * pues forman parte del historial clínico del paciente.
 */
public interface OrderExecutionRecordPort {
    void save(OrderExecutionRecord record) throws Exception;
    List<OrderExecutionRecord> findByOrderItem(OrderItem item) throws Exception;
}