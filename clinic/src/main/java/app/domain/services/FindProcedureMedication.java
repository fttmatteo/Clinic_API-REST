package app.domain.services;

import app.domain.model.ProcedureMedication;
import app.domain.ports.ProcedureMedicationPort;

public class FindProcedureMedication {
    private ProcedureMedicationPort procedureMedicationPort;

    public FindProcedureMedication(ProcedureMedicationPort procedureMedicationPort) {
        this.procedureMedicationPort = procedureMedicationPort;
    }

    public ProcedureMedication findById(Long id) throws Exception {
        ProcedureMedication found = procedureMedicationPort.findById(id);
        if (found == null) {
            throw new Exception("No existe un procedimiento/medicamento registrado con ese ID");
        }
        return found;
    }
}
