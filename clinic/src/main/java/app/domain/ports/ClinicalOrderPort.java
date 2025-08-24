package app.domain.ports;

import app.domain.model.ClinicalOrder;
public interface ClinicalOrderPort {
    void save(ClinicalOrder clinicalOrder) throws Exception;
}
