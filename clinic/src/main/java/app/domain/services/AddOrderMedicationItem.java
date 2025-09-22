package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.OrderMedicationItem;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.OrderMedicationItemPort;

@Service
public class AddOrderMedicationItem {

    @Autowired private ClinicalOrderPort clinicalOrderPort;
    @Autowired private OrderMedicationItemPort orderMedicationItemPort;

    public OrderMedicationItem add(OrderMedicationItem orderMedicationItem) throws Exception {
        if (clinicalOrderPort.findByOrderId(orderMedicationItem.getNumberOrder()) == null) {
            throw new BusinessException("la orden no existe");
        }
        List<OrderMedicationItem> items = orderMedicationItemPort.listByNumberOrder(orderMedicationItem.getNumberOrder());
        for (OrderMedicationItem it : items) {
            if (it.getItem() == orderMedicationItem.getItem()) {
                throw new BusinessException("ya existe el item " + orderMedicationItem.getItem() +
                        " para la orden " + orderMedicationItem.getNumberOrder());
            }
        }
        return orderMedicationItemPort.save(orderMedicationItem);
    }
}
