package app.domain.ports;

import java.util.List;

import app.domain.model.Procedure;

/**
 * Puerto de persistencia para operaciones relacionadas con los
 * procedimientos médicos. Permite almacenar y consultar procedimientos
 * manteniendo la separación respecto a la tecnología de persistencia.
 */
public interface ProcedurePort {
    void save(Procedure procedure) throws Exception;
    Procedure findById(String id) throws Exception;
    List<Procedure> findAll() throws Exception;
}