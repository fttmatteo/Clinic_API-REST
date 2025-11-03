package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para campos de procedimientos médicos.
 */
@Component
public class ProcedureValidator extends SimpleValidator {
    public String idValidator(String value) throws InputsException {
        return stringValidator("identificador del procedimiento", value);
    }
    public String nameValidator(String value) throws InputsException {
        return stringValidator("nombre del procedimiento", value);
    }
    public Double costValidator(String value) throws InputsException {
        return doubleValidator("costo del procedimiento", value);
    }
    public Integer quantityValidator(String value) throws InputsException {
        int quantity = integerValidator("cantidad por defecto del procedimiento", value);
        if (quantity < 1) {
            throw new InputsException("la cantidad por defecto del procedimiento debe ser mayor a cero");
        }
        return quantity;
    }
    public String frequencyValidator(String value) throws InputsException {
        return stringValidator("frecuencia por defecto del procedimiento", value);
    }
    public Boolean requiresSpecialistValidator(String value) throws InputsException {
        stringValidator("indicador de especialista por defecto del procedimiento", value);
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized) || "si".equals(normalized)) {
            return true;
        }
        if ("false".equals(normalized) || "no".equals(normalized)) {
            return false;
        }
        throw new InputsException("el indicador de especialista del procedimiento debe ser si/no o true/false");
    }
}
