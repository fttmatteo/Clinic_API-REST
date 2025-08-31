package app.domain.services;

import app.domain.model.ProcedureMedication;
import app.domain.ports.ProcedureMedicationPort;

public class EditProcedureMedication {
    private ProcedureMedicationPort procedureMedicationPort;

    public EditProcedureMedication(ProcedureMedicationPort procedureMedicationPort) {
        this.procedureMedicationPort = procedureMedicationPort;
    }

    public void edit(ProcedureMedication procedureMedication) throws Exception {
        if (procedureMedication == null) {
            throw new Exception("El procedimiento/medicamento es nulo");
        }
        procedureMedicationPort.save(procedureMedication);
    }
}
