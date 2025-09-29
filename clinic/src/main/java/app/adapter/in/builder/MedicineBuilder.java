package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.MedicineValidator;
import app.domain.model.inventory.Medicine;

/**
 * Builder para construir instancias de {@link Medicine} a partir de
 * cadenas recibidas en peticiones. Utiliza {@link MedicineValidator}
 * para validar y convertir los campos.
 */
@Component
public class MedicineBuilder {
    @Autowired
    private MedicineValidator validator;

    public Medicine build(String id, String name, String cost) throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId(validator.idValidator(id));
        medicine.setName(validator.nameValidator(name));
        medicine.setCost(validator.costValidator(cost));
        return medicine;
    }
}