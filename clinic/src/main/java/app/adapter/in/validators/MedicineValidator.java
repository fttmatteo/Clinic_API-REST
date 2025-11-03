package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para campos de medicamentos. Amplía {@link SimpleValidator}
 * proporcionando verificaciones específicas para identificador, nombre y
 * costo.
 */
@Component
public class MedicineValidator extends SimpleValidator {
    public String idValidator(String value) throws InputsException {
        return stringValidator("identificador del medicamento", value);
    }
    public String nameValidator(String value) throws InputsException {
        return stringValidator("nombre del medicamento", value);
    }
    public Double costValidator(String value) throws InputsException {
        return doubleValidator("costo del medicamento", value);
    }
    public String doseValidator(String value) throws InputsException {
        return stringValidator("dosis por defecto del medicamento", value);
    }
    public String treatmentDurationValidator(String value) throws InputsException {
        return stringValidator("duracion del tratamiento por defecto del medicamento", value);
    }
}
