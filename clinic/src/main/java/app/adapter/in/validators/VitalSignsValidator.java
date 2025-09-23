package app.adapter.in.validators;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class VitalSignsValidator extends SimpleValidator {

    public int patientDocumentValidator(String value) throws Exception {
        digitsMax("documento del paciente", value, 10);
        return intValidator("documento del paciente", value);
    }

    public Date attentionDateValidator(String value) throws Exception {
        return dateValidator("fecha de registro", value);
    }

    public String bloodPressureValidatorField(String value) throws Exception {
        return bloodPressureValidator(value);
    }

    public float temperatureValidator(String value) throws Exception {
        return rangeFloat("temperatura", value, 30f, 45f);
    }

    public int pulseValidator(String value) throws Exception {
        return rangeInt("pulso", value, 30, 220);
    }

    public int oxygenSaturationValidator(String value) throws Exception {
        return rangeInt("saturación de oxígeno", value, 0, 100);
    }
}
