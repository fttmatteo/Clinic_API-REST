package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.OrderProcedureItem;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.OrderProcedureItemPort;

@Service
public class AddOrderProcedureItem {

    @Autowired private ClinicalOrderPort clinicalOrderPort;
    @Autowired private OrderProcedureItemPort orderProcedureItemPort;

    public OrderProcedureItem add(OrderProcedureItem orderProcedureItem) throws Exception {
        if (clinicalOrderPort.findByOrderId(orderProcedureItem.getNumberOrder()) == null) {
            throw new BusinessException("la orden no existe");
        }
        List<OrderProcedureItem> items = orderProcedureItemPort.listByNumberOrder(orderProcedureItem.getNumberOrder());
        for (OrderProcedureItem it : items) {
            if (it.getItem() == orderProcedureItem.getItem()) {
                throw new BusinessException("ya existe el item " + orderProcedureItem.getItem() +
                        " para la orden " + orderProcedureItem.getNumberOrder());
            }
        }
        return orderProcedureItemPort.save(orderProcedureItem);
    }
}
