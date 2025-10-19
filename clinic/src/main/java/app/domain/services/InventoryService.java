package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.model.Medicine;
import app.domain.model.Procedure;

/**
 * Servicio de dominio encargado de exponer el catálogo de medicamentos,
 * procedimientos y ayudas diagnósticas disponibles en la clínica.
 */
@Service
public class InventoryService {

    @Autowired
    private SearchMedicine searchMedicine;
    @Autowired
    private SearchProcedure searchProcedure;
    @Autowired
    private SearchDiagnosticAid searchDiagnosticAid;

    public List<Medicine> getMedicines() throws Exception {
        return searchMedicine.findAll();
    }

    public List<Procedure> getProcedures() throws Exception {
        return searchProcedure.findAll();
    }

    public List<DiagnosticAid> getDiagnosticAids() throws Exception {
        return searchDiagnosticAid.findAll();
    }

    public Medicine findMedicineById(String id) throws Exception {
        return searchMedicine.findById(id);
    }

    public Procedure findProcedureById(String id) throws Exception {
        return searchProcedure.findById(id);
    }
    
    public DiagnosticAid findDiagnosticAidById(String id) throws Exception {
        return searchDiagnosticAid.findById(id);
    }
}