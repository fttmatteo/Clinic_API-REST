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
}