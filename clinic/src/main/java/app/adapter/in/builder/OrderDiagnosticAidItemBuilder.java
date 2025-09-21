package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderDiagnosticAidItemValidator;
import app.domain.model.OrderDiagnosticAidItem;

@Component
public class OrderDiagnosticAidItemBuilder {

    @Autowired private OrderDiagnosticAidItemValidator validator;

    public OrderDiagnosticAidItem build(
        String numberOrder,
        String item,
        String idDiagnosticAid,
        String quantity,
        String specialistRequired,
        String specialistTypeId,
        String price
    ) throws Exception {

        OrderDiagnosticAidItem orderDiagnosticAidItem = new OrderDiagnosticAidItem();
        orderDiagnosticAidItem.setNumberOrder(validator.numberOrderValidator(numberOrder));
        orderDiagnosticAidItem.setItem(validator.itemValidator(item));
        orderDiagnosticAidItem.setIdDiagnosticAid(validator.idDiagnosticAidValidator(idDiagnosticAid));
        orderDiagnosticAidItem.setQuantity(validator.quantityValidator(quantity));
        orderDiagnosticAidItem.setSpecialistRequired(validator.specialistRequiredValidator(specialistRequired));
        orderDiagnosticAidItem.setSpecialistTypeId(validator.specialistTypeIdValidator(specialistTypeId));
        orderDiagnosticAidItem.setPrice(validator.costValidator(price));
        return orderDiagnosticAidItem;
    }
}
