package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los datos básicos de una orden médica. Verifica el
 * identificador de la orden, que debe ser numérico y de máximo seis
 * dígitos.
 */
@Component
public class MedicalOrderValidator extends SimpleValidator {

    public long idValidator(String value) throws InputsException {
        long id = longValidator("número de la orden", value);
        if (String.valueOf(id).length() > 6) {
            throw new InputsException("el número de la orden no puede exceder 6 dígitos");
        }
        return id;
    }
}