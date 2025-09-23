package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.ClinicalHistoryValidator;
import app.domain.model.ClinicalHistory;

@Component
public class ClinicalHistoryBuilder {

    @Autowired private ClinicalHistoryValidator validator;

    public ClinicalHistory build(
        String patientDocument,
        String attentionDate,
        String doctorDocument,
        String motive,
        String symptoms,
        String diagnosis
    ) throws Exception {

        ClinicalHistory clinicalHistory = new ClinicalHistory();
        clinicalHistory.setPatientDocument(validator.patientDocumentValidator(patientDocument));
        clinicalHistory.setAttentionDate(validator.attentionDateValidator(attentionDate));
        clinicalHistory.setDoctorDocument(validator.doctorDocumentValidator(doctorDocument));
        clinicalHistory.setMotive(validator.motiveValidator(motive));
        clinicalHistory.setSymptoms(validator.symptomsValidator(symptoms));
        clinicalHistory.setDiagnosis(validator.diagnosisValidator(diagnosis));
        return clinicalHistory;
    }
}
