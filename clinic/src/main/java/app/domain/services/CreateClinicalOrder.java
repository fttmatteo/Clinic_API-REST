package app.domain.services;

import app.domain.model.ClinicalOrder;
import app.domain.model.Patient;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.PatientPort;

import java.sql.Date;

public class CreateClinicalOrder {
    private final PatientPort patientPort;
    private final ClinicalOrderPort clinicalOrderPort;

    public CreateClinicalOrder(PatientPort patientPort, ClinicalOrderPort clinicalOrderPort) {
        this.patientPort = patientPort;
        this.clinicalOrderPort = clinicalOrderPort;
    }

    public void create(ClinicalOrder clinicalOrder) throws Exception {
    }       
}
