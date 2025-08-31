package app.domain.services;

import app.domain.model.Diagnostic;
import app.domain.ports.DiagnosticPort;

public class FindDiagnostic {
    private DiagnosticPort diagnosticPort;

    public FindDiagnostic(DiagnosticPort diagnosticPort) {
        this.diagnosticPort = diagnosticPort;
    }

    public Diagnostic findById(Diagnostic diagnostic) throws Exception {
        Diagnostic found = diagnosticPort.findById(diagnostic);
        if (found == null) {
            throw new Exception("No existe un diagnóstico registrado con ese ID");
        }
        return found;
    }
}
