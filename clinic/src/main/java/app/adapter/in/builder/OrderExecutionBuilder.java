package app.adapter.in.builder;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderExecutionValidator;
import app.domain.model.Employee;
import app.domain.model.OrderExecutionRecord;

/**
 * Builder para {@link OrderExecutionRecord}. Convierte los datos de entrada
 * en un objeto de dominio y aplica las validaciones necesarias.
 */
@Component
public class OrderExecutionBuilder {

    @Autowired
    private OrderExecutionValidator validator;

    public OrderExecutionRecord build(String nurseDocument, String amount, String notes) throws Exception {
        OrderExecutionRecord record = new OrderExecutionRecord();
        Employee nurse = new Employee();
        nurse.setDocument(validator.nurseDocumentValidator(nurseDocument));
        record.setNurse(nurse);
        record.setAmount(validator.amountValidator(amount));
        record.setNotes(validator.notesValidator(notes));
        record.setDateTime(new Timestamp(System.currentTimeMillis()));
        return record;
    }
}