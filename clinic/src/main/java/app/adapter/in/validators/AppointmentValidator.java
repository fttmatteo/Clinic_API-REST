package app.adapter.in.validators;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los campos de la creación de citas. Convierte los valores
 * proporcionados como cadenas a los tipos apropiados y aplica reglas
 * básicas de formato.
 */
@Component
public class AppointmentValidator extends SimpleValidator {

    public long patientDocumentValidator(String value) throws InputsException {
        return longValidator("documento del paciente", value);
    }

    public long doctorDocumentValidator(String value) throws InputsException {
        return longValidator("número de cédula del médico", value);
    }

    public Timestamp dateTimeValidator(String value) throws InputsException {
        stringValidator("fecha y hora de la cita", value);
        String normalized = value.trim().replace('T', ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime ldt = LocalDateTime.parse(normalized, formatter);
            return Timestamp.valueOf(ldt);
        } catch (DateTimeParseException e) {
            throw new InputsException("la fecha y hora debe tener el formato YYYY-MM-DD HH:MM");
        }
    }
}
