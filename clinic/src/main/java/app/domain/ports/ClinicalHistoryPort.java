package app.domain.ports;

import java.util.List;
import app.domain.model.ClinicalHistory;

public interface ClinicalHistoryPort {
    ClinicalHistory save(ClinicalHistory clinicalHistory) throws Exception;
    List<ClinicalHistory> findByPatient(Integer patientDocument) throws Exception;
}
