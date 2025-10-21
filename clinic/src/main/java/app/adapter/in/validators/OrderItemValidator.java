package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;
import app.domain.model.enums.OrderItemType;

/**
 * Validador para los campos de un ítem de orden. Dependiendo del tipo de
 * ítem se aplican reglas diferentes. Por ejemplo, un medicamento debe
 * indicar dosis y duración, mientras que un procedimiento requiere
 * cantidad, frecuencia y puede requerir un especialista.
 */
@Component
public class OrderItemValidator extends SimpleValidator {

    public int itemNumberValidator(String value) throws InputsException {
        int number = integerValidator("número de ítem", value);
        if (number < 1) {
            throw new InputsException("el número de ítem debe ser mayor o igual a 1");
        }
        return number;
    }

    public OrderItemType typeValidator(String value) throws InputsException {
        stringValidator("tipo de ítem", value);

        String norm = java.text.Normalizer.normalize(value.trim().toLowerCase(), java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[\\s-]+", "_");

        switch (norm) {
            case "medicamento":
            case "medicamentos":
                return OrderItemType.MEDICINE;

            case "procedimiento":
            case "procedimientos":
                return OrderItemType.PROCEDURE;

            case "ayuda_diagnostica":
            case "diagnostico":
            case "diagnosticos":
                return OrderItemType.DIAGNOSTIC_AID;

            default:
                try {
                    return OrderItemType.valueOf(value.trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InputsException("el tipo de ítem debe ser medicamento, procedimiento o ayuda diagnóstica.");
                }
        }
    }

    public String nameValidator(String value) throws InputsException {
        return stringValidator("nombre del ítem", value);
    }

    public String doseValidator(String value, OrderItemType type) throws InputsException {
        if (type == OrderItemType.MEDICINE) {
            return stringValidator("dosis del medicamento", value);
        }
        return null;
    }

    public String treatmentDurationValidator(String value, OrderItemType type) throws InputsException {
        if (type == OrderItemType.MEDICINE) {
            return stringValidator("duración del tratamiento", value);
        }
        return null;
    }

    public Integer quantityValidator(String value, OrderItemType type) throws InputsException {
        if (type == OrderItemType.PROCEDURE || type == OrderItemType.DIAGNOSTIC_AID) {
            return integerValidator("cantidad", value);
        }
        return null;
    }

    public String frequencyValidator(String value, OrderItemType type) throws InputsException {
        if (type == OrderItemType.PROCEDURE) {
            return stringValidator("frecuencia del procedimiento", value);
        }
        return null;
    }

    public Double costValidator(String value) throws InputsException {
        return doubleValidator("costo", value);
    }

    public Boolean requiresSpecialistValidator(String value, OrderItemType type) throws InputsException {
        if (type == OrderItemType.PROCEDURE || type == OrderItemType.DIAGNOSTIC_AID) {
            stringValidator("requiere especialista", value);
            String lower = value.toLowerCase();
            if (lower.equals("si") || lower.equals("true")) {
                return true;
            }
            if (lower.equals("no") || lower.equals("false")) {
                return false;
            }
            throw new InputsException("requiere especialista debe ser si/no o true/false");
        }
        return null;
    }

    public String specialistTypeIdValidator(String value, OrderItemType type, Boolean requiresSpecialist) throws InputsException {
        if ((type == OrderItemType.PROCEDURE || type == OrderItemType.DIAGNOSTIC_AID) && Boolean.TRUE.equals(requiresSpecialist)) {
            return stringValidator("id del tipo de especialidad", value);
        }
        return null;
    }
}