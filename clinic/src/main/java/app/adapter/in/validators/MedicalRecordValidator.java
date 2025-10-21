package app.adapter.in.validators;

import org.springframework.stereotype.Component;

/**
 * Validador para los campos de la historia clínica. Asegura que los campos
 * obligatorios como motivo, síntomas y diagnóstico no estén vacíos.
 */
@Component
public class MedicalRecordValidator extends SimpleValidator {
    public String motiveValidator(String value) throws Exception {
        return stringValidator("motivo de la consulta", value);
    }
    public String symptomsValidator(String value) throws Exception {
        return stringValidator("sintomatología", value);
    }
    public String diagnosisValidator(String value) throws Exception {
        return stringValidator("diagnóstico", value);
    }
}