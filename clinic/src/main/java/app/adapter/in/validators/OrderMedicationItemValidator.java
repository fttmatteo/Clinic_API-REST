package app.adapter.in.validators;

import app.application.exceptions.InputsException;
import org.springframework.stereotype.Component;

@Component
public class OrderMedicationItemValidator extends SimpleValidator {

    public int numberOrderValidator(String value) throws Exception {
        digitsMax("número de orden", value, 6);
        return intValidator("número de orden", value);
    }

    public int itemValidator(String value) throws Exception {
        int n = intValidator("ítem", value);
        if (n < 1) throw new InputsException("ítem debe iniciar en 1");
        return n;
    }

    public int idMedicationValidator(String value) throws Exception {
        return intValidator("id del medicamento", value);
    }

    public int doseValidator(String value) throws Exception {
        return intValidator("dosis", value);
    }

    public String treatmentDurationValidator(String value) throws Exception {
        return maxLength("duración del tratamiento", value, 80);
    }

    public long costValidator(String value) throws Exception {
        long c = longValidator("costo", value);
        if (c < 0) throw new InputsException("costo no puede ser negativo");
        return c;
    }
}
