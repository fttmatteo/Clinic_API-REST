package app.application.usecase;

import app.domain.model.MedicationInventory;
import app.domain.model.ProcedureInventory;
import app.domain.model.DiagnosticInventory;
import app.domain.services.UpdateMedicationInventory;
import app.domain.services.UpdateProcedureInventory;
import app.domain.services.UpdateDiagnosticInventory;

public class SupportUseCase {
    private final UpdateMedicationInventory updateMedicationInventory;
    private final UpdateProcedureInventory updateProcedureInventory;
    private final UpdateDiagnosticInventory updateDiagnosticInventory;

    public SupportUseCase(UpdateMedicationInventory updateMedicationInventory,
                          UpdateProcedureInventory updateProcedureInventory,
                          UpdateDiagnosticInventory updateDiagnosticInventory) {
        this.updateMedicationInventory = updateMedicationInventory;
        this.updateProcedureInventory = updateProcedureInventory;
        this.updateDiagnosticInventory = updateDiagnosticInventory;
    }

    public void actualizarMedicamento(String id, String nombre, int cantidad) throws Exception {
        MedicationInventory medication = new MedicationInventory(id, nombre, cantidad);
        updateMedicationInventory.update(medication);
    }

    public void actualizarProcedimiento(String id, String nombre, int cantidad) throws Exception {
        ProcedureInventory procedure = new ProcedureInventory(id, nombre, cantidad);
        updateProcedureInventory.update(procedure);
    }

    public void actualizarAyudaDiagnostica(String id, String nombre, int cantidad) throws Exception {
        DiagnosticInventory diagnostic = new DiagnosticInventory(id, nombre, cantidad);
        updateDiagnosticInventory.update(diagnostic);
    }
}
