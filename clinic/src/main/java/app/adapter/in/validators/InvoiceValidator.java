package app.adapter.in.validators;

import java.sql.Date;
import app.application.exceptions.InputsException;
import org.springframework.stereotype.Component;

@Component
public class InvoiceValidator extends SimpleValidator {

    public int patientDocumentValidator(String value) throws Exception {
        digitsMax("documento del paciente", value, 10);
        return intValidator("documento del paciente", value);
    }

    public long totalServiceValidator(String value) throws Exception {
        long t = longValidator("total del servicio", value);
        if (t < 0) throw new InputsException("total del servicio no puede ser negativo");
        return t;
    }

    public Date invoiceDateValidator(String value) throws Exception {
        return dateValidator("fecha de la factura", value);
    }
}
