package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;
import app.domain.model.enums.OrderItemType;

/**
 * Validador para los campos de un item de orden. Se limita a validar que el
 * tipo del item corresponda a un valor permitido y que el identificador de
 * inventario no sea vacio.
 */
@Component
public class OrderItemValidator extends SimpleValidator {

    public OrderItemType typeValidator(String value) throws InputsException {
        stringValidator("tipo de item", value);

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
                    throw new InputsException("el tipo de item debe ser medicamento, procedimiento o ayuda diagnostica.");
                }
        }
    }

    public String referenceIdValidator(String value) throws InputsException {
        return stringValidator("identificador del item", value);
    }
}