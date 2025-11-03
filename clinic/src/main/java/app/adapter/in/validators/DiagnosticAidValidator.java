package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para campos de ayudas diagnosticas.
 */
@Component
public class DiagnosticAidValidator extends SimpleValidator {
    public String idValidator(String value) throws InputsException {
        return stringValidator("identificador de la ayuda diagnostica", value);
    }
    public String nameValidator(String value) throws InputsException {
        return stringValidator("nombre de la ayuda diagnostica", value);
    }
    public Double costValidator(String value) throws InputsException {
        return doubleValidator("costo de la ayuda diagnostica", value);
    }
    public Integer quantityValidator(String value) throws InputsException {
        int quantity = integerValidator("cantidad por defecto de la ayuda diagnostica", value);
        if (quantity < 1) {
            throw new InputsException("la cantidad por defecto de la ayuda diagnostica debe ser mayor a cero");
        }
        return quantity;
    }
    public Boolean requiresSpecialistValidator(String value) throws InputsException {
        stringValidator("indicador de especialista por defecto de la ayuda diagnostica", value);
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized) || "si".equals(normalized)) {
            return true;
        }
        if ("false".equals(normalized) || "no".equals(normalized)) {
            return false;
        }
        throw new InputsException("el indicador de especialista de la ayuda diagnostica debe ser si/no o true/false");
    }
}