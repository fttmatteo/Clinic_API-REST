package app.domain.ports;

import java.util.List;
import app.domain.model.ClinicalOrder;

public interface ClinicalOrderPort {
    ClinicalOrder findByOrderId(Long numberOrder) throws Exception;
    List<ClinicalOrder> findByPatient(Integer patientDocument) throws Exception;
    ClinicalOrder save(ClinicalOrder clinicalOrder) throws Exception;
}
