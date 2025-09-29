package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para campos de ayudas diagnósticas.
 */
@Component
public class DiagnosticAidValidator extends SimpleValidator {
    public String idValidator(String value) throws InputsException {
        return stringValidator("identificador de la ayuda diagnóstica", value);
    }
    public String nameValidator(String value) throws InputsException {
        return stringValidator("nombre de la ayuda diagnóstica", value);
    }
    public Double costValidator(String value) throws InputsException {
        return doubleValidator("costo de la ayuda diagnóstica", value);
    }
}