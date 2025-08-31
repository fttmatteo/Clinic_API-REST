package app.domain.services;

import app.domain.model.Diagnostic;
import app.domain.ports.DiagnosticPort;

public class EditDiagnostic {
    private DiagnosticPort diagnosticPort;

    public EditDiagnostic(DiagnosticPort diagnosticPort) {
        this.diagnosticPort = diagnosticPort;
    }

    public void edit(Diagnostic diagnostic) throws Exception {
        if (diagnostic == null) {
            throw new Exception("El diagnóstico es nulo");
        }
        diagnosticPort.save(diagnostic);
    }
}
