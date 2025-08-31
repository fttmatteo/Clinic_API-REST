package app.domain.services;

import app.domain.model.ProcedureMedication;
import app.domain.ports.ProcedureMedicationPort;

public class ProcedureMedicationService {
    private ProcedureMedicationPort procedureMedicationPort;

    public ProcedureMedicationService(ProcedureMedicationPort procedureMedicationPort) {
        this.procedureMedicationPort = procedureMedicationPort;
    }

    public void saveProcedureMedication(ProcedureMedication procedureMedication) throws Exception {
        procedureMedicationPort.save(procedureMedication);
    }

    public ProcedureMedication findProcedureMedicationById(Long id) throws Exception {
        return procedureMedicationPort.findById(id);
    }
}
