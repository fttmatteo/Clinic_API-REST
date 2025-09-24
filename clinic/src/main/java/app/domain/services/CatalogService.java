package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Medicine;
import app.domain.model.Procedure;
import app.domain.model.DiagnosticAid;
import app.domain.ports.MedicinePort;
import app.domain.ports.ProcedurePort;
import app.domain.ports.DiagnosticAidPort;

@Service
public class CatalogService {
    
    @Autowired private MedicinePort medicinePort;
    @Autowired private ProcedurePort procedurePort;
    @Autowired private DiagnosticAidPort diagnosticAidPort;

    public Medicine findMedicineById(Long id) throws Exception {
        if (id == null) throw new Exception("El id del medicamento es obligatorio");
        Medicine m = medicinePort.findById(id);
        if (m == null) throw new Exception("No existe el medicamento solicitado");
        return m;
    }

    public List<Medicine> listMedicines() throws Exception {
        return medicinePort.listAll();
    }

    public Medicine createMedicine(Medicine medicine) throws Exception {
        if (medicine == null) throw new Exception("El medicamento es obligatorio");
        if (medicine.getMedicineName() == null || medicine.getMedicineName().isBlank())
            throw new Exception("El nombre del medicamento es obligatorio");
        if (medicine.getPrice() == null || medicine.getPrice() < 0)
            throw new Exception("El precio del medicamento es inválido");
        return medicinePort.save(medicine);
    }

    public Medicine updateMedicine(Medicine medicine) throws Exception {
        if (medicine == null || medicine.getMedicineId() == null)
            throw new Exception("El id del medicamento es obligatorio");
        if (medicine.getMedicineName() == null || medicine.getMedicineName().isBlank())
            throw new Exception("El nombre del medicamento es obligatorio");
        if (medicine.getPrice() == null || medicine.getPrice() < 0)
            throw new Exception("El precio del medicamento es inválido");
        return medicinePort.update(medicine);
    }

    public void deleteMedicine(Long id) throws Exception {
        if (id == null) throw new Exception("El id del medicamento es obligatorio");
        medicinePort.delete(id);
    }

    public Procedure findProcedureById(Long id) throws Exception {
        if (id == null) throw new Exception("El id del procedimiento es obligatorio");
        Procedure p = procedurePort.findById(id);
        if (p == null) throw new Exception("No existe el procedimiento solicitado");
        return p;
    }

    public List<Procedure> listProcedures() throws Exception {
        return procedurePort.listAll();
    }

    public Procedure createProcedure(Procedure procedure) throws Exception {
        if (procedure == null) throw new Exception("El procedimiento es obligatorio");
        if (procedure.getProcedureName() == null || procedure.getProcedureName().isBlank())
            throw new Exception("El nombre del procedimiento es obligatorio");
        if (procedure.getPrice() == null || procedure.getPrice() < 0)
            throw new Exception("El precio del procedimiento es inválido");
        return procedurePort.save(procedure);
    }

    public Procedure updateProcedure(Procedure procedure) throws Exception {
        if (procedure == null || procedure.getProcedureId() == null)
            throw new Exception("El id del procedimiento es obligatorio");
        if (procedure.getProcedureName() == null || procedure.getProcedureName().isBlank())
            throw new Exception("El nombre del procedimiento es obligatorio");
        if (procedure.getPrice() == null || procedure.getPrice() < 0)
            throw new Exception("El precio del procedimiento es inválido");
        return procedurePort.update(procedure);
    }

    public void deleteProcedure(Long id) throws Exception {
        if (id == null) throw new Exception("El id del procedimiento es obligatorio");
        procedurePort.delete(id);
    }

    public DiagnosticAid findDiagnosticAidById(Long id) throws Exception {
        if (id == null) throw new Exception("El id de la ayuda diagnóstica es obligatorio");
        DiagnosticAid d = diagnosticAidPort.findById(id);
        if (d == null) throw new Exception("No existe la ayuda diagnóstica solicitada");
        return d;
    }

    public List<DiagnosticAid> listDiagnosticAids() throws Exception {
        return diagnosticAidPort.listAll();
    }

    public DiagnosticAid createDiagnosticAid(DiagnosticAid diagnosticAid) throws Exception {
        if (diagnosticAid == null) throw new Exception("La ayuda diagnóstica es obligatoria");
        if (diagnosticAid.getDiagnosticAidName() == null || diagnosticAid.getDiagnosticAidName().isBlank())
            throw new Exception("El nombre de la ayuda diagnóstica es obligatorio");
        if (diagnosticAid.getPrice() == null || diagnosticAid.getPrice() < 0)
            throw new Exception("El precio de la ayuda diagnóstica es inválido");
        return diagnosticAidPort.save(diagnosticAid);
    }

    public DiagnosticAid updateDiagnosticAid(DiagnosticAid diagnosticAid) throws Exception {
        if (diagnosticAid == null || diagnosticAid.getDiagnosticAidId() == null)
            throw new Exception("El id de la ayuda diagnóstica es obligatorio");
        if (diagnosticAid.getDiagnosticAidName() == null || diagnosticAid.getDiagnosticAidName().isBlank())
            throw new Exception("El nombre de la ayuda diagnóstica es obligatorio");
        if (diagnosticAid.getPrice() == null || diagnosticAid.getPrice() < 0)
            throw new Exception("El precio de la ayuda diagnóstica es inválido");
        return diagnosticAidPort.update(diagnosticAid);
    }

    public void deleteDiagnosticAid(Long id) throws Exception {
        if (id == null) throw new Exception("El id de la ayuda diagnóstica es obligatorio");
        diagnosticAidPort.delete(id);
    }
}
