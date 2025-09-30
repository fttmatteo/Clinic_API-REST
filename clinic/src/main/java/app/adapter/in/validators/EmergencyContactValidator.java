package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para el contacto de emergencia de un paciente. Verifica los
 * campos básicos como nombre, apellido, relación y teléfono de contacto.
 */
@Component
public class EmergencyContactValidator extends SimpleValidator {
    public String firstNameValidator(String value) throws InputsException {
        return stringValidator("nombres del contacto de emergencia", value);
    }
    public String lastNameValidator(String value) throws InputsException {
        return stringValidator("apellidos del contacto de emergencia", value);
    }
    public String relationShipValidator(String value) throws InputsException {
        return stringValidator("relación del contacto de emergencia", value);
    }
    public String phoneValidator(String value) throws InputsException {
        stringValidator("teléfono del contacto de emergencia", value);
        if (!value.matches("\\d{10}")) {
            throw new InputsException("el teléfono de emergencia debe contener exactamente 10 dígitos");
        }
        return value;
    }
}