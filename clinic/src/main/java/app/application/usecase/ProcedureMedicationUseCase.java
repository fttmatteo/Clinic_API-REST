package app.application.usecase;

import app.domain.model.ProcedureMedication;
import app.domain.ports.ProcedureMedicationPort;

public class ProcedureMedicationUseCase {
    private ProcedureMedicationPort procedureMedicationPort;

    public ProcedureMedicationUseCase(ProcedureMedicationPort procedureMedicationPort) {
        this.procedureMedicationPort = procedureMedicationPort;
    }

    public void saveProcedureMedication(ProcedureMedication procedureMedication) throws Exception {
        procedureMedicationPort.save(procedureMedication);
    }

    public ProcedureMedication findProcedureMedicationById(Long id) throws Exception {
        return procedureMedicationPort.findById(id);
    }
}
