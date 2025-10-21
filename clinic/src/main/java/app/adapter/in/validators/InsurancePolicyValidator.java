package app.adapter.in.validators;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los datos de una póliza de seguro médico. Incluye la
 * compañía aseguradora, el número de póliza, el estado de la póliza y la
 * fecha de vencimiento.
 */
@Component
public class InsurancePolicyValidator extends SimpleValidator {
    public String companyNameValidator(String value) throws InputsException {
        return stringValidator("nombre de la compañía de seguros", value);
    }
    public String policyNumberValidator(String value) throws InputsException {
        return stringValidator("número de póliza", value);
    }
    public boolean activeValidator(String value) throws InputsException {
        stringValidator("estado de la póliza", value);
        String lower = value.toLowerCase();
        if (lower.equals("si") || lower.equals("true")) {
            return true;
        }
        if (lower.equals("no") || lower.equals("false")) {
            return false;
        }
        throw new InputsException("el estado de la póliza debe ser si/no o true/false");
    }
    public Date expiryDateValidator(String value) throws InputsException {
        stringValidator("fecha de finalización de la póliza", value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            return Date.valueOf(date);
        } catch (DateTimeParseException e) {
            throw new InputsException("la fecha de finalización debe tener el formato DD/MM/YYYY");
        }
    }
}