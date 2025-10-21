package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para las facturas. Comprueba el nombre del producto, el monto
 * facturado y los identificadores de paciente, médico y orden cuando
 * corresponda.
 */
@Component
public class InvoiceValidator extends SimpleValidator {
    public String productNameValidator(String value) throws InputsException {
        return stringValidator("nombre del producto", value);
    }
    public double amountValidator(String value) throws InputsException {
        return doubleValidator("monto de la factura", value);
    }
    public long patientIdValidator(String value) throws InputsException {
        return longValidator("id del paciente", value);
    }
    public long doctorDocumentValidator(String value) throws InputsException {
        return longValidator("cédula del médico", value);
    }
    public boolean isMedicineValidator(String value) throws InputsException {
        stringValidator("indicación de medicamento", value);
        String lower = value.toLowerCase();
        if (lower.equals("si") || lower.equals("true")) {
            return true;
        }
        if (lower.equals("no") || lower.equals("false")) {
            return false;
        }
        throw new InputsException("debe indicar si la factura es de medicamento (si/no)");
    }
    public long orderIdValidator(String value) throws InputsException {
        return longValidator("id de la orden", value);
    }
}