package app.domain.ports;

import java.util.List;
import app.domain.model.OrderProcedureItem;

public interface OrderProcedureItemPort {
    public List<OrderProcedureItem> listByNumberOrder (int numberOrder) throws Exception;
    public OrderProcedureItem save(OrderProcedureItem orderProcedureItem) throws Exception;
}
