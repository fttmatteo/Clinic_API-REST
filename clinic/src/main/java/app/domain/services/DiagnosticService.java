package app.domain.services;

import app.domain.model.Diagnostic;
import app.domain.ports.DiagnosticPort;

public class DiagnosticService {
    private DiagnosticPort diagnosticPort;

    public DiagnosticService(DiagnosticPort diagnosticPort) {
        this.diagnosticPort = diagnosticPort;
    }

    public void saveDiagnostic(Diagnostic diagnostic) throws Exception {
        diagnosticPort.save(diagnostic);
    }

    public Diagnostic findDiagnosticById(Long id) throws Exception {
        return diagnosticPort.findById(id);
    }
}
