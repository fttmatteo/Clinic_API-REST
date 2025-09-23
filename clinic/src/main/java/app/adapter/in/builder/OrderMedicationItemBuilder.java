package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderMedicationItemValidator;
import app.domain.model.OrderMedicationItem;

@Component
public class OrderMedicationItemBuilder {

    @Autowired private OrderMedicationItemValidator validator;

    public OrderMedicationItem build(
        String numberOrder,
        String item,
        String medicineId,
        String dose,
        String treatmentDuration,
        String price
    ) throws Exception {

        OrderMedicationItem orderMedicationItem = new OrderMedicationItem();
        orderMedicationItem.setNumberOrder(validator.numberOrderValidator(numberOrder));
        orderMedicationItem.setItem(validator.itemValidator(item));
        orderMedicationItem.setMedicineId(validator.medicineIdValidator(medicineId));
        orderMedicationItem.setDose(validator.doseValidator(dose));
        orderMedicationItem.setTreatmentDuration(validator.treatmentDurationValidator(treatmentDuration));
        orderMedicationItem.setPrice(validator.priceValidator(price));
        return orderMedicationItem;
    }
}
