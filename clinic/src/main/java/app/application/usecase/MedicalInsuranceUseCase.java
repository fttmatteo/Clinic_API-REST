package app.application.usecase;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class MedicalInsuranceUseCase {
    private MedicalInsurancePort medicalInsurancePort;

    public MedicalInsuranceUseCase(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public void saveMedicalInsurance(MedicalInsurance insurance) throws Exception {
        medicalInsurancePort.save(insurance);
    }

    public MedicalInsurance findMedicalInsuranceById(Long id) throws Exception {
        return medicalInsurancePort.findById(id);
    }
}
