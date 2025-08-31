package app.domain.services;

import app.domain.model.MedicalInsurance;
import app.domain.ports.MedicalInsurancePort;

public class CreateMedicalInsurance {
    private MedicalInsurancePort medicalInsurancePort;

    public void create(MedicalInsurance insurance) throws Exception {
        if (insurance == null) {
            throw new Exception("El seguro médico es nulo");
        }
        medicalInsurancePort.save(insurance);
    }

    public MedicalInsurance findById(Long id) throws Exception {
        MedicalInsurance found = medicalInsurancePort.findById(id);
        if (found == null) {
            throw new Exception("No existe un seguro médico registrado con ese ID");
        }
        return found;
    }
}
