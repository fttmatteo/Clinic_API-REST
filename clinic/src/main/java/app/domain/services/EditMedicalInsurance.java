package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class EditMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public EditMedicalInsurance(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public void edit(MedicalInsurance medicalInsurance) throws Exception {
        if (medicalInsurance == null) {
            throw new Exception("El seguro médico es nulo");
        }
        medicalInsurancePort.save(medicalInsurance);
    }
}
