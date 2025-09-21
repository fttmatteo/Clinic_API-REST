package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class PatientValidator extends SimpleValidator {

    public int documentValidator(String value) throws Exception {
        digitsMax("documento del paciente", value, 10);
        return intValidator("documento del paciente", value);
    }

    public String fullNameValidator(String value) throws Exception {
        return maxLength("nombre completo", value, 120);
    }

    public Date birthDateValidator(String value) throws Exception {
        return dateValidator("fecha de nacimiento", value);
    }

    public String genderValidator(String value) throws Exception {
        return maxLength("género", value, 20);
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

    public String emergencyFirstNameValidator(String value) throws Exception {
        return maxLength("nombre de emergencia", value, 80);
    }

    public String emergencyLastNameValidator(String value) throws Exception {
        return maxLength("apellido de emergencia", value, 80);
    }

    public String relationShipValidator(String value) throws Exception {
        return maxLength("relación de emergencia", value, 40);
    }

    public int emergencyPhoneValidator(String value) throws Exception {
        digitsExact("teléfono de emergencia", value, 10);
        return intValidator("teléfono de emergencia", value);
    }
}
