package app.domain.ports;

import app.domain.model.ProcedureMedication;

public interface ProcedureMedicationPort {
    ProcedureMedication findById(Long id) throws Exception;
    void save(ProcedureMedication procedureMedication) throws Exception;
}
