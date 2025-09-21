package app.domain.ports;

import java.util.List;
import app.domain.model.OrderDiagnosticAidItem;

public interface OrderDiagnosticAidItemPort {
    public List<OrderDiagnosticAidItem> listByNumberOrder (int numberOrder) throws Exception;
    public OrderDiagnosticAidItem save(OrderDiagnosticAidItem orderDiagnosticAidItem) throws Exception;
}
