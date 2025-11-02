package app.adapter.in.validators;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los campos relacionados con los empleados. Amplía
 * {@link SimpleValidator} añadiendo validaciones específicas como
 * comprobación del formato de fecha, verificación de correo electrónico,
 * longitud del teléfono y reglas de contraseña.
 */
@Component
public class EmployeeValidator extends SimpleValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\n]+@[^@\n]+\\.[^@\n]+$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    public String fullNameValidator(String value) throws InputsException {
        return stringValidator("nombre completo", value);
    }

    public long documentValidator(String value) throws InputsException {
        long doc = longValidator("número de cédula", value);
        if (String.valueOf(Math.abs(doc)).length() > 10) {
            throw new InputsException("la cédula no puede exceder 10 dígitos");
        }
        return doc;
    }

    public Date birthDateValidator(String value) throws InputsException {
        stringValidator("fecha de nacimiento", value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            int age = Period.between(date, LocalDate.now()).getYears();
            if (age > 150) {
                throw new InputsException("la edad no puede ser mayor a 150 años");
            }
            return Date.valueOf(date);
        } catch (DateTimeParseException e) {
            throw new InputsException("la fecha de nacimiento debe tener el formato DD/MM/YYYY");
        }
    }

    public String addressValidator(String value) throws InputsException {
        return stringValidator("dirección", value);
    }

    public String phoneValidator(String value) throws InputsException {
        stringValidator("número de teléfono", value);
        if (!value.matches("\\d{1,10}")) {
            throw new InputsException("el número de teléfono debe contener entre 1 y 10 dígitos");
        }
        return value;
    }

    public String emailValidator(String value) throws InputsException {
        stringValidator("correo electrónico", value);
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InputsException("el correo electrónico debe tener un formato válido");
        }
        return value;
    }

    public String userNameValidator(String value) throws InputsException {
        stringValidator("nombre de usuario", value);
        if (value.length() > 15) {
            throw new InputsException("el nombre de usuario no puede exceder 15 caracteres");
        }
        if (!USERNAME_PATTERN.matcher(value).matches()) {
            throw new InputsException("el nombre de usuario solo debe contener letras y números");
        }
        return value;
    }

    public String passwordValidator(String value) throws InputsException {
        stringValidator("contraseña", value);
        if (value.length() < 8) {
            throw new InputsException("la contraseña debe contener al menos 8 caracteres");
        }
        if (!value.matches(".*[A-Z].*")) {
            throw new InputsException("la contraseña debe contener al menos una letra mayúscula");
        }
        if (!value.matches(".*[0-9].*")) {
            throw new InputsException("la contraseña debe contener al menos un número");
        }
        // Verificar que exista al menos un carácter especial no alfanumérico
        if (!value.matches(".*[^A-Za-z0-9].*")) {
            throw new InputsException("la contraseña debe contener al menos un carácter especial");
        }
        return value;
    }
}