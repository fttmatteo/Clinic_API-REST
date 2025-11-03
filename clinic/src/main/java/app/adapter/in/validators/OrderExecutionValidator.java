package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los datos de registro de administración de un ítem de una
 * orden médica. Valida los valores numéricos y cadenas básicos.
 */
@Component
public class OrderExecutionValidator extends SimpleValidator {

    public long nurseDocumentValidator(String value) throws InputsException {
        return longValidator("número de cédula de la enfermera", value);
    }

    public double amountValidator(String value) throws InputsException {
        double result = doubleValidator("cantidad aplicada", value);
        if (result < 0) {
            throw new InputsException("la cantidad aplicada debe ser un valor positivo");
        }
        return result;
    }

    public String notesValidator(String value) throws InputsException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        if (value.length() > 1024) {
            throw new InputsException("las observaciones no pueden exceder 1024 caracteres");
        }
        return value;
    }
}