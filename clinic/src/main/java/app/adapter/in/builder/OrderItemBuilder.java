package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderItemValidator;
import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;

/**
 * Builder para construir instancias de {@link OrderItem} a partir de los
 * datos recibidos en una petición. Utiliza {@link OrderItemValidator} para
 * validar los campos según el tipo de ítem.
 */
@Component
public class OrderItemBuilder {
    @Autowired
    private OrderItemValidator validator;

    public OrderItem build(String itemNumber, String type, String referenceId,
                           String dose, String treatmentDuration,
                           String quantity, String frequency,
                           String cost, String requiresSpecialist,
                           String specialistTypeId) throws Exception {
        OrderItem item = new OrderItem();
        OrderItemType itemType = validator.typeValidator(type);
        item.setItemNumber(validator.itemNumberValidator(itemNumber));
        item.setType(itemType);
        item.setName(validator.referenceIdValidator(referenceId));
        item.setDose(validator.doseValidator(dose, itemType));
        item.setTreatmentDuration(validator.treatmentDurationValidator(treatmentDuration, itemType));
        item.setQuantity(validator.quantityValidator(quantity, itemType));
        item.setFrequency(validator.frequencyValidator(frequency, itemType));
        item.setCost(validator.costValidator(cost));
        Boolean requires = validator.requiresSpecialistValidator(requiresSpecialist, itemType);
        item.setRequiresSpecialist(requires);
        item.setSpecialistTypeId(validator.specialistTypeIdValidator(specialistTypeId, itemType, requires));
        return item;
    }
}