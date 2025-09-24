package app.domain.ports;

import java.util.List;
import app.domain.model.DiagnosticAid;

public interface DiagnosticAidPort {
    DiagnosticAid findById(Long id) throws Exception;
    List<DiagnosticAid> listAll() throws Exception;
    DiagnosticAid save(DiagnosticAid diagnosticAid) throws Exception;
    DiagnosticAid update(DiagnosticAid diagnosticAid) throws Exception;
    void delete(Long id) throws Exception;
}
