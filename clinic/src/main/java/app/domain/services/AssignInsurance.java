package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.MedicalInsurance;
import app.domain.model.Patient;
import app.domain.ports.PatientPort;

@Service
public class AssignInsurance {

    @Autowired
    private PatientPort patientPort;

    public Patient assign(int documentPatient, MedicalInsurance medicalInsurance) throws Exception {
        Patient patient = patientPort.findByPatient(documentPatient);
        if (patient == null) throw new BusinessException("el paciente no existe");
        patient.setInsurancePolicy(medicalInsurance);
        return patientPort.save(patient);
    }
}
