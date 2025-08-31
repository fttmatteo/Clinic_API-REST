package app.domain.services;

import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;

import java.sql.Date;

public class CreateClinicalHistory {
    private final ClinicalHistoryPort clinicalHistoryPort;

    public CreateClinicalHistory(ClinicalHistoryPort clinicalHistoryPort) {
        this.clinicalHistoryPort = clinicalHistoryPort;
    }

    public void create(ClinicalHistory history) throws Exception {
        if (history.getDate() == null) {
            history.setDate(new Date(System.currentTimeMillis()));
        }
        if (history.getMotive() == null || history.getMotive().isEmpty()) {
            throw new Exception("El motivo de la consulta es obligatorio");
        }
        if (history.getSymptoms() == null || history.getSymptoms().isEmpty()) {
            throw new Exception("La sintomatología es obligatoria");
        }
        if (history.getDiagnosis() == null || history.getDiagnosis().isEmpty()) {
            throw new Exception("El diagnóstico es obligatorio");
        }

        clinicalHistoryPort.save(history);
    }
}
