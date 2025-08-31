package app.domain.ports;

import app.domain.model.MedicalInsurance;

public interface MedicalInsurancePort {
    MedicalInsurance findById(MedicalInsurance medicalInsurance) throws Exception;
    void save(MedicalInsurance medicalInsurance) throws Exception;
}
