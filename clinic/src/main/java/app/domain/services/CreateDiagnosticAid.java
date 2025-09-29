package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.inventory.DiagnosticAid;
import app.domain.ports.DiagnosticAidPort;

/**
 * Servicio de dominio responsable de crear ayudas diagnósticas en el catálogo.
 * Garantiza que el identificador sea único antes de delegar la persistencia
 * al puerto correspondiente.
 */
@Service
public class CreateDiagnosticAid {
    @Autowired
    private DiagnosticAidPort diagnosticAidPort;

    public void create(DiagnosticAid aid) throws Exception {
        if (diagnosticAidPort.findById(aid.getId()) != null) {
            throw new BusinessException("ya existe una ayuda diagnóstica con ese identificador");
        }
        diagnosticAidPort.save(aid);
    }
}