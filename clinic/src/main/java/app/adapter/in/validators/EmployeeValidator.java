package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator extends SimpleValidator {

    public int documentValidator(String value) throws Exception {
        digitsMax("documento del empleado", value, 10);
        return intValidator("documento del empleado", value);
    }

    public String fullNameValidator(String value) throws Exception {
        return maxLength("nombre completo", value, 120);
    }

    public Date birthDateValidator(String value) throws Exception {
        return dateValidator("fecha de nacimiento", value);
    }

    public String addressValidator(String value) throws Exception {
        return maxLength("dirección", value, 100);
    }

    public int phoneNumberValidator(String value) throws Exception {
        digitsExact("teléfono", value, 10);
        return intValidator("teléfono", value);
    }

    public String emailValidatorField(String value) throws Exception {
        return emailValidator(value);
    }

    public String usernameValidator(String value) throws Exception {
        return maxLength("usuario", value, 15); // regla: ≤ 15
    }

    public String passwordValidator(String value) throws Exception {
        return maxLength("contraseña", value, 100);
    }

    public String roleValidator(String value) throws Exception {
        // Validación básica de no vacío; la conversión a enum va en el mapeo
        return maxLength("rol", value, 30);
    }
}
