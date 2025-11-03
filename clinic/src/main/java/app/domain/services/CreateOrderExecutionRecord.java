package app.domain.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.OrderExecutionRecord;
import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;
import app.domain.ports.EmployeePort;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.OrderExecutionRecordPort;

/**
 * Servicio de dominio para registrar la aplicación de medicamentos o
 * realización de procedimientos. Verifica que el ítem corresponda a un
 * medicamento o procedimiento (no una ayuda diagnóstica) y que existan la
 * orden médica y la enfermera.
 */
@Service
public class CreateOrderExecutionRecord {

    @Autowired
    private MedicalOrderPort orderPort;

    @Autowired
    private EmployeePort employeePort;

    @Autowired
    private OrderExecutionRecordPort recordPort;

    public void create(OrderExecutionRecord record, Long orderId, Integer itemNumber) throws Exception {
        MedicalOrder orderProbe = new MedicalOrder();
        orderProbe.setId(orderId);
        MedicalOrder order = orderPort.findById(orderProbe);
        if (order == null) {
            throw new BusinessException("la orden médica no existe");
        }
        Optional<OrderItem> optItem = order.getItems().stream()
            .filter(it -> it.getItemNumber() == itemNumber)
            .findFirst();
        if (!optItem.isPresent()) {
            throw new BusinessException("el número de ítem no existe en la orden médica");
        }
        OrderItem item = optItem.get();
        if (OrderItemType.DIAGNOSTIC_AID.equals(item.getType())) {
            throw new BusinessException("no se pueden registrar ayudas diagnósticas como administradas por enfermería");
        }
        Employee nurse = record.getNurse();
        if (nurse == null) {
            throw new BusinessException("se requiere la identificación de la enfermera");
        }
        nurse = employeePort.findByDocument(nurse);
        if (nurse == null || !app.domain.model.enums.Role.NURSE.equals(nurse.getRole())) {
            throw new BusinessException("la identificación de la enfermera no es válida");
        }
        record.setOrderItem(item);
        record.setNurse(nurse);
        if (record.getDateTime() == null) {
            record.setDateTime(new Timestamp(System.currentTimeMillis()));
        }
        recordPort.save(record);
    }
}