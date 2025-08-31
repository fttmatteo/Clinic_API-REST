package app.domain.ports;

import app.domain.model.MedicalInsurance;

public interface MedicalInsurancePort {
    MedicalInsurance findById(Long id) throws Exception;
    void save(MedicalInsurance insurance) throws Exception;
}
