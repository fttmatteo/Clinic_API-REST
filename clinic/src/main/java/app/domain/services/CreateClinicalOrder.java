package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.ClinicalOrder;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.PatientPort;

@Service
public class CreateClinicalOrder {

    @Autowired private ClinicalOrderPort clinicalOrderPort;
    @Autowired private PatientPort patientPort;

    public ClinicalOrder create(ClinicalOrder clinicalOrder) throws Exception {
        if (clinicalOrderPort.findById(clinicalOrder.getId()) != null) {
            throw new BusinessException("ya existe una orden con ese número");
        }
        if (patientPort.findByPatient(clinicalOrder.getPatientDocument()) == null) {
            throw new BusinessException("el paciente no existe");
        }
        return clinicalOrderPort.save(clinicalOrder);
    }
}
