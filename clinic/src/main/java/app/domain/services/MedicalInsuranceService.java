package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;
public class MedicalInsuranceService {
    private MedicalInsurancePort medicalInsurancePort;

    public MedicalInsuranceService(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public void saveMedicalInsurance(MedicalInsurance insurance) throws Exception {
        medicalInsurancePort.save(insurance);
    }

    public MedicalInsurance findMedicalInsuranceById(Long id) throws Exception {
        return medicalInsurancePort.findById(id);
    }
}
