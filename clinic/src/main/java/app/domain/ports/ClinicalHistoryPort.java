package app.domain.ports;

import java.util.List;

import app.domain.model.ClinicalHistory;

public interface ClinicalHistoryPort {
    public ClinicalHistory save (ClinicalHistory clinicalHistory) throws Exception;
    public List<ClinicalHistory> findByPatient (int patientDocument) throws Exception;
}
