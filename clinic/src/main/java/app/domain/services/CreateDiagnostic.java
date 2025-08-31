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

    public Diagnostic findById(Long id) throws Exception {
        Diagnostic found = diagnosticPort.findById(id);
        if (found == null) {
            throw new Exception("No existe un diagnóstico registrado con ese ID");
        }
        return found;
    }
}
