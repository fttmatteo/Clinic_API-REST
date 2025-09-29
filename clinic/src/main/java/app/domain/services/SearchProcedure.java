package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.inventory.Procedure;
import app.domain.ports.ProcedurePort;

/**
 * Servicio de dominio para consultar procedimientos médicos en el catálogo.
 */
@Service
public class SearchProcedure {
    @Autowired
    private ProcedurePort procedurePort;

    public List<Procedure> findAll() throws Exception {
        return procedurePort.findAll();
    }

    public Procedure findById(String id) throws Exception {
        return procedurePort.findById(id);
    }
}