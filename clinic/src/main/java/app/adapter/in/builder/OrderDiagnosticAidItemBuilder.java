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
        String cost
    ) throws Exception {

        OrderDiagnosticAidItem it = new OrderDiagnosticAidItem();
        it.setNumberOrder(validator.numberOrderValidator(numberOrder));
        it.setItem(validator.itemValidator(item));
        it.setIdDiagnosticAid(validator.idDiagnosticAidValidator(idDiagnosticAid));
        it.setQuantity(validator.quantityValidator(quantity));
        it.setSpecialistRequired(validator.specialistRequiredValidator(specialistRequired));
        it.setSpecialistTypeId(validator.specialistTypeIdValidator(specialistTypeId));
        it.setPrice(validator.costValidator(cost));
        return it;
    }
}
