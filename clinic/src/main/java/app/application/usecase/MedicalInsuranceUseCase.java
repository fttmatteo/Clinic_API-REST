package app.application.usecase;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class MedicalInsuranceUseCase {
    private MedicalInsurancePort medicalInsurancePort;

    public MedicalInsuranceUseCase(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public void saveMedicalInsurance(MedicalInsurance medicalInsurance) throws Exception {
        medicalInsurancePort.save(medicalInsurance);
    }

    public MedicalInsurance findMedicalInsuranceById(MedicalInsurance medicalInsurance) throws Exception {
        return medicalInsurancePort.findById(medicalInsurance);
    }
}
