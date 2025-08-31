package app.domain.services;

import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;

import java.sql.Date;

public class CreateClinicalHistory {
    private final ClinicalHistoryPort clinicalHistoryPort;

    public CreateClinicalHistory(ClinicalHistoryPort clinicalHistoryPort) {
        this.clinicalHistoryPort = clinicalHistoryPort;
    }

    public void create(ClinicalHistory clinicalHistory) throws Exception {
        if (clinicalHistory.getDate() == null) {
            clinicalHistory.setDate(new Date(System.currentTimeMillis()));
        }
        if (clinicalHistory.getMotive() == null || clinicalHistory.getMotive().isEmpty()) {
            throw new Exception("El motivo de la consulta es obligatorio");
        }
        if (clinicalHistory.getSymptoms() == null || clinicalHistory.getSymptoms().isEmpty()) {
            throw new Exception("La sintomatología es obligatoria");
        }
        if (clinicalHistory.getDiagnosis() == null || clinicalHistory.getDiagnosis().isEmpty()) {
            throw new Exception("El diagnóstico es obligatorio");
        }

        clinicalHistoryPort.save(clinicalHistory);
    }
}
