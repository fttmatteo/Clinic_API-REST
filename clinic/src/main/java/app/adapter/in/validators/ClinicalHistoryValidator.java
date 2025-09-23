package app.adapter.in.validators;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class ClinicalHistoryValidator extends SimpleValidator {

    public int patientDocumentValidator(String value) throws Exception {
        digitsMax("documento del paciente", value, 10);
        return intValidator("documento del paciente", value);
    }

    public Date attentionDateValidator(String value) throws Exception {
        return dateValidator("fecha de atención", value);
    }

    public int doctorDocumentValidator(String value) throws Exception {
        digitsMax("documento del profesional", value, 10);
        return intValidator("documento del profesional", value);
    }

    public String motiveValidator(String value) throws Exception {
        return maxLength("motivo de la consulta", value, 250);
    }

    public String symptomsValidator(String value) throws Exception {
        return maxLength("síntomas", value, 500);
    }

    public String diagnosisValidator(String value) throws Exception {
        return maxLength("diagnóstico", value, 500);
    }
}
