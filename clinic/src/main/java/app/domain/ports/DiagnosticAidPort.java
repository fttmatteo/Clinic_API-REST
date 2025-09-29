package app.domain.ports;

import java.util.List;

import app.domain.model.inventory.DiagnosticAid;

/**
 * Puerto de persistencia para operaciones relacionadas con las ayudas
 * diagnósticas.  Proporciona métodos para guardar, buscar y listar
 * ayudas diagnósticas sin depender de la capa de persistencia.
 */
public interface DiagnosticAidPort {
    void save(DiagnosticAid aid) throws Exception;
    DiagnosticAid findById(String id) throws Exception;
    List<DiagnosticAid> findAll() throws Exception;
}