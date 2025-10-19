package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.ports.DiagnosticAidPort;

/**
 * Servicio de dominio para consultar ayudas diagnósticas en el catálogo.
 */
@Service
public class SearchDiagnosticAid {
    @Autowired
    private DiagnosticAidPort diagnosticAidPort;

    public List<DiagnosticAid> findAll() throws Exception {
        return diagnosticAidPort.findAll();
    }

    public DiagnosticAid findById(String id) throws Exception {
        return diagnosticAidPort.findById(id);
    }
}