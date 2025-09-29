package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.inventory.Procedure;
import app.domain.ports.ProcedurePort;

/**
 * Servicio de dominio responsable de crear procedimientos en el catálogo.
 * Aplica la regla de negocio de unicidad del identificador antes de
 * delegar la persistencia al puerto de infraestructura.
 */
@Service
public class CreateProcedure {
    @Autowired
    private ProcedurePort procedurePort;

    public void create(Procedure procedure) throws Exception {
        if (procedurePort.findById(procedure.getId()) != null) {
            throw new BusinessException("ya existe un procedimiento con ese identificador");
        }
        procedurePort.save(procedure);
    }
}