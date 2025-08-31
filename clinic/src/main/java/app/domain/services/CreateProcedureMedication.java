package app.domain.services;

import app.domain.model.ProcedureMedication;
import app.domain.ports.ProcedureMedicationPort;

public class CreateProcedureMedication {
    private ProcedureMedicationPort procedureMedicationPort;

    public void create(ProcedureMedication procedureMedication) throws Exception {
        if (procedureMedication == null) {
            throw new Exception("El procedimiento/medicamento es nulo");
        }
        procedureMedicationPort.save(procedureMedication);
    }

    public ProcedureMedication findById(Long id) throws Exception {
        ProcedureMedication found = procedureMedicationPort.findById(id);
        if (found == null) {
            throw new Exception("No existe un procedimiento/medicamento registrado con ese ID");
        }
        return found;
    }
}
