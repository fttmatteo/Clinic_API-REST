package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class EditMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public EditMedicalInsurance(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public void edit(MedicalInsurance insurance) throws Exception {
        if (insurance == null) {
            throw new Exception("El seguro médico es nulo");
        }
        medicalInsurancePort.save(insurance);
    }
}
