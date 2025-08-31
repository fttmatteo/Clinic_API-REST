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
        if (clinicalOrder.getMedic() == null || clinicalOrder.getMedic().getDocument() == 0) {
            throw new Exception("Debe indicar el documento del médico");
        }
        if (clinicalOrder.getPatient() == null || clinicalOrder.getPatient().getDocument() == 0) {
            throw new Exception("Debe indicar el documento del paciente");
        }
        Patient patientQuery = new Patient();
        patientQuery.setDocument(clinicalOrder.getPatient().getDocument());
        Patient patient = patientPort.findByDocument(patientQuery);
        if (patient == null) {
            throw new Exception("La orden debe asociarse a un paciente registrado");
        }

        if (clinicalOrder.getDate() == null) {
            clinicalOrder.setDate(new Date(System.currentTimeMillis()));
        }

        clinicalOrderPort.save(clinicalOrder);
    }
}
