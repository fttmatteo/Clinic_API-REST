package app.adapter.in.validators;

import org.springframework.stereotype.Component;

@Component
public class ClinicalOrderValidator extends SimpleValidator {

    public int numberOrderValidator(String value) throws Exception {
        digitsMax("número de orden", value, 6);
        return intValidator("número de orden", value);
    }

    public int patientDocumentValidator(String value) throws Exception {
        digitsMax("documento del paciente", value, 10);
        return intValidator("documento del paciente", value);
    }

    public int doctorDocumentValidator(String value) throws Exception {
        digitsMax("documento del profesional", value, 10);
        return intValidator("documento del profesional", value);
    }

    public java.sql.Date creationDateValidator(String value) throws Exception {
        return dateValidator("fecha de creación", value);
    }
}
