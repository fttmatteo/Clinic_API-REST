package app.domain.ports;

import app.domain.model.Diagnostic;

public interface DiagnosticPort {
    Diagnostic findById(Diagnostic diagnostic) throws Exception;
    void save(Diagnostic diagnostic) throws Exception;
}
