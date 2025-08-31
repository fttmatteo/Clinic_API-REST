package app.domain.ports;

import app.domain.model.Diagnostic;

public interface DiagnosticPort {
    Diagnostic findById(Long id) throws Exception;
    void save(Diagnostic diagnostic) throws Exception;
}
