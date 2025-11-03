package app.adapter.in.validators;

import org.springframework.stereotype.Component;

import app.application.exceptions.InputsException;

/**
 * Validador para las facturas. Comprueba los identificadores de paciente,
 * médico y orden clínica asociados a la facturación.
 */
@Component
public class InvoiceValidator extends SimpleValidator {
    public long patientDocumentValidator(String value) throws InputsException {
        return longValidator("documento del paciente", value);
    }
    public long doctorDocumentValidator(String value) throws InputsException {
        return longValidator("cédula del médico", value);
    }
    public String orderNumberValidator(String value) throws InputsException {
        return stringValidator("número de la orden", value);
    }
}
