package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.VitalSigns;
import app.domain.ports.PatientPort;
import app.domain.ports.VitalSignsPort;

@Service
public class RecordVitalSigns {

    @Autowired private VitalSignsPort vitalSignsPort;
    @Autowired private PatientPort patientPort;

    public VitalSigns create(VitalSigns vitalSigns) throws Exception {
        if (patientPort.findByPatient(vitalSigns.getPatientDocument()) == null) {
            throw new BusinessException("el paciente no existe");
        }
        return vitalSignsPort.save(vitalSigns);
    }
}
