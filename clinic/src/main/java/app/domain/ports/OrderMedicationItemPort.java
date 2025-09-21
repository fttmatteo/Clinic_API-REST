package app.domain.ports;

import java.util.List;
import app.domain.model.OrderMedicationItem;

public interface OrderMedicationItemPort {
    public List<OrderMedicationItem> listByNumberOrder (int numberOrder) throws Exception;
    public OrderMedicationItem save(OrderMedicationItem orderMedicationItem) throws Exception;
}
