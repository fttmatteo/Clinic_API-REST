package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.OrderDiagnosticAidItem;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.OrderDiagnosticAidItemPort;

@Service
public class AddOrderDiagnosticAidItem {

    @Autowired private ClinicalOrderPort clinicalOrderPort;
    @Autowired private OrderDiagnosticAidItemPort orderDiagnosticAidItemPort;

    public OrderDiagnosticAidItem add(OrderDiagnosticAidItem orderDiagnosticAidItem) throws Exception {
        if (clinicalOrderPort.findByOrderId(orderDiagnosticAidItem.getNumberOrder()) == null) {
            throw new BusinessException("la orden no existe");
        }
        List<OrderDiagnosticAidItem> items = orderDiagnosticAidItemPort.listByNumberOrder(orderDiagnosticAidItem.getNumberOrder());
        for (OrderDiagnosticAidItem it : items) {
            if (it.getItem() == orderDiagnosticAidItem.getItem()) {
                throw new BusinessException("ya existe el item " + orderDiagnosticAidItem.getItem() +
                        " para la orden " + orderDiagnosticAidItem.getNumberOrder());
            }
        }
        return orderDiagnosticAidItemPort.save(orderDiagnosticAidItem);
    }
}
