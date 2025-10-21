package app.adapter.in.validators;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;
import app.domain.model.enums.Gender;

/**
 * Validador para los campos de un paciente. Verifica la consistencia de
 * datos como nombre, documento, fecha de nacimiento, género, dirección,
 * teléfono, correo, nombre de usuario y contraseña.
 */
@Component
public class PatientValidator extends SimpleValidator {

    public String fullNameValidator(String value) throws InputsException {
        return stringValidator("nombre completo", value);
    }

    public long documentValidator(String value) throws InputsException {
        return longValidator("número de identificación", value);
    }

    public Date birthDateValidator(String value) throws InputsException {
        stringValidator("fecha de nacimiento", value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            int age = Period.between(date, LocalDate.now()).getYears();
            if (age > 150) {
                throw new InputsException("la fecha de nacimiento no puede indicar una edad mayor a 150 años");
            }
            return Date.valueOf(date);
        } catch (DateTimeParseException e) {
            throw new InputsException("la fecha de nacimiento debe tener el formato DD/MM/YYYY");
        }
    }

    public Gender genderValidator(String value) throws InputsException {
        stringValidator("género", value);
        String v = value.trim().toLowerCase();

        for (Gender g : Gender.values()) {
            if (g.name().equalsIgnoreCase(v)) {
                return g;
            }
        }

        switch (v) {
            case "masculino":
            case "m":
                return Gender.MALE;
            case "femenino":
            case "f":
                return Gender.FEMALE;
            case "otro":
            case "o":
                return Gender.OTHER;
            default:
                throw new InputsException("el género debe ser masculino, femenino u otro");
        }
    }

    public String addressValidator(String value) throws InputsException {
        return stringValidator("dirección", value);
    }

    public String phoneValidator(String value) throws InputsException {
        stringValidator("número de teléfono", value);
        if (!value.matches("\\d{10}")) {
            throw new InputsException("el número de teléfono debe contener exactamente 10 dígitos");
        }
        return value;
    }

    public String emailValidator(String value) throws InputsException {
        return new EmployeeValidator().emailValidator(value);
    }

}