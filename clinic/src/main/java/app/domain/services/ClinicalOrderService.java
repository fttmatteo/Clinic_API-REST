package app.domain.services;

import app.domain.model.ClinicalOrder;
import app.domain.model.Patient;
import app.domain.model.User;
import app.domain.ports.ClinicalOrderPort;
import app.domain.ports.PatientPort;
import app.domain.ports.UserPort;

import java.sql.Date;

public class CreateClinicalOrder {
    private final UserPort userPort;
    private final PatientPort patientPort;
    private final ClinicalOrderPort clinicalOrderPort;

    public CreateClinicalOrder(UserPort userPort, PatientPort patientPort, ClinicalOrderPort clinicalOrderPort) {
        this.userPort = userPort;
        this.patientPort = patientPort;
        this.clinicalOrderPort = clinicalOrderPort;
    }

    public void create(ClinicalOrder clinicalOrder) throws Exception {
        if (clinicalOrder.getMedic() == null || clinicalOrder.getMedic().getDocument() == null) {
            throw new Exception("Debe indicar el documento del médico");
        }
        User doctorQuery = new User();
        if (clinicalOrder.getPatient() == null || clinicalOrder.getPatient().getDocument() == null) {
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
