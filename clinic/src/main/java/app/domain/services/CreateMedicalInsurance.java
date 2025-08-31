package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class CreateMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public void create(MedicalInsurance medicalInsurance) throws Exception {
        if (medicalInsurance == null) {
            throw new Exception("El seguro médico es nulo");
        }
        medicalInsurancePort.save(medicalInsurance);
    }
}
