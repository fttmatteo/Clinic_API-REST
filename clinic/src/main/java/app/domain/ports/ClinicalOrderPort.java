package app.domain.ports;

import java.util.List;
import app.domain.model.ClinicalOrder;

public interface ClinicalOrderPort {
    public ClinicalOrder findByOrderId (int numberOrder) throws Exception;
    public List<ClinicalOrder> findByPatient (int patientDocument) throws Exception;
    public ClinicalOrder save(ClinicalOrder clinicalOrder) throws Exception;
}
