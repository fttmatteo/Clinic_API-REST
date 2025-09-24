package app.domain.ports;

import java.util.List;
import app.domain.model.Procedure;

public interface ProcedurePort {
    Procedure findById(Long id) throws Exception;
    List<Procedure> listAll() throws Exception;
    Procedure save(Procedure procedure) throws Exception;
    Procedure update(Procedure procedure) throws Exception;
    void delete(Long id) throws Exception;
}
