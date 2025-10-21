package app.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.services.CreateMedicine;
import app.domain.services.CreateProcedure;
import app.domain.model.DiagnosticAid;
import app.domain.model.Medicine;
import app.domain.model.Procedure;
import app.domain.services.CreateDiagnosticAid;
import app.domain.services.SearchMedicine;
import app.domain.services.SearchProcedure;
import app.domain.services.SearchDiagnosticAid;

/**
 * Caso de uso que reúne las operaciones de soporte de información para
 * administrar el inventario de medicamentos, procedimientos y ayudas
 * diagnósticas.  Este caso de uso se utiliza por el personal de
 * soporte para crear elementos del catálogo y consultarlos.
 */
@Service
public class InformationSupportUseCase {
    @Autowired
    private CreateMedicine createMedicine;
    @Autowired
    private CreateProcedure createProcedure;
    @Autowired
    private CreateDiagnosticAid createDiagnosticAid;
    @Autowired
    private SearchMedicine searchMedicine;
    @Autowired
    private SearchProcedure searchProcedure;
    @Autowired
    private SearchDiagnosticAid searchDiagnosticAid;

    public void createMedicine(Medicine medicine) throws Exception {
        createMedicine.create(medicine);
    }
    public void createProcedure(Procedure procedure) throws Exception {
        createProcedure.create(procedure);
    }
    public void createDiagnosticAid(DiagnosticAid aid) throws Exception {
        createDiagnosticAid.create(aid);
    }
    public List<Medicine> getMedicines() throws Exception {
        return searchMedicine.findAll();
    }
    public List<Procedure> getProcedures() throws Exception {
        return searchProcedure.findAll();
    }
    public List<DiagnosticAid> getDiagnosticAids() throws Exception {
        return searchDiagnosticAid.findAll();
    }
}