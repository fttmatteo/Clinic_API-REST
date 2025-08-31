package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class FindMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public FindMedicalInsurance(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public MedicalInsurance findById(MedicalInsurance medicalInsurance) throws Exception {
        MedicalInsurance found = medicalInsurancePort.findById(medicalInsurance);
        if (found == null) {
            throw new Exception("No existe un seguro médico registrado con ese ID");
        }
        return found;
    }
}
