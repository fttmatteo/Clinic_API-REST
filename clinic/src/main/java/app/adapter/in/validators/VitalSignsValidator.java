package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para los registros de signos vitales. Comprueba el formato de
 * presión arterial, temperatura, pulso y nivel de oxígeno antes de que
 * sean procesados por el dominio.
 */
@Component
public class VitalSignsValidator extends SimpleValidator {
    public String bloodPressureValidator(String value) throws InputsException {
        stringValidator("presión arterial", value);
        if (!value.matches("\\d{2,3}/\\d{2,3}")) {
            throw new InputsException("la presión arterial debe tener el formato NN/NN");
        }
        return value;
    }
    public double temperatureValidator(String value) throws InputsException {
        return doubleValidator("temperatura", value);
    }
    public int pulseValidator(String value) throws InputsException {
        return integerValidator("pulso", value);
    }
    public int oxygenLevelValidator(String value) throws InputsException {
        int level = integerValidator("nivel de oxígeno", value);
        if (level < 0 || level > 100) {
            throw new InputsException("el nivel de oxígeno debe estar entre 0 y 100");
        }
        return level;
    }
}