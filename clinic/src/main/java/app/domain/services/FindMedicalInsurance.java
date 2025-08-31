package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class FindMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public FindMedicalInsurance(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    public MedicalInsurance findById(Long id) throws Exception {
        MedicalInsurance found = medicalInsurancePort.findById(id);
        if (found == null) {
            throw new Exception("No existe un seguro médico registrado con ese ID");
        }
        return found;
    }
}
