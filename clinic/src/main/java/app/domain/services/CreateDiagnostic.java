package app.domain.services;

import app.domain.model.Diagnostic;
import app.domain.ports.DiagnosticPort;

public class CreateDiagnostic {
    private DiagnosticPort diagnosticPort;

    public void create(Diagnostic diagnostic) throws Exception {
        if (diagnostic == null) {
            throw new Exception("El diagnóstico es nulo");
        }
        diagnosticPort.save(diagnostic);
    }
}
