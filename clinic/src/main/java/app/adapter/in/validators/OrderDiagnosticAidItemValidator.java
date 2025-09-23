package app.adapter.in.validators;

import app.application.exceptions.InputsException;
import org.springframework.stereotype.Component;

@Component
public class OrderDiagnosticAidItemValidator extends SimpleValidator {

    public int numberOrderValidator(String value) throws Exception {
        digitsMax("número de orden", value, 6);
        return intValidator("número de orden", value);
    }

    public int itemValidator(String value) throws Exception {
        int n = intValidator("ítem", value);
        if (n < 1) throw new InputsException("ítem debe iniciar en 1");
        return n;
    }

    public int diagnosticAidValidator(String value) throws Exception {
        return intValidator("id de la ayuda diagnóstica", value);
    }

    public int quantityValidator(String value) throws Exception {
        int q = intValidator("cantidad", value);
        if (q < 1) throw new InputsException("cantidad debe ser al menos 1");
        return q;
    }

    public boolean specialistRequiredValidator(String value) throws Exception {
        return booleanValidator("requiere especialista", value);
    }

    public int specialistIdValidator(String value) throws Exception {
        return intValidator("tipo de especialista", value);
    }

    public long priceValidator(String value) throws Exception {
        long c = longValidator("costo", value);
        if (c < 0) throw new InputsException("costo no puede ser negativo");
        return c;
    }
}
