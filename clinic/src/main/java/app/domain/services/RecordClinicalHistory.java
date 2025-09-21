package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;
import app.domain.ports.PatientPort;

@Service
public class RecordClinicalHistory {

    @Autowired private ClinicalHistoryPort clinicalHistoryPort;
    @Autowired private PatientPort patientPort;

    public ClinicalHistory create(ClinicalHistory clinicalHistory) throws Exception {
        if (patientPort.findByPatient(clinicalHistory.getPatientDocument()) == null) {
            throw new BusinessException("el paciente no existe");
        }
        return clinicalHistoryPort.save(clinicalHistory);
    }
}
