package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.ProcedureValidator;
import app.domain.model.Procedure;

/**
 * Builder para construir objetos {@link Procedure} a partir de datos
 * proporcionados desde peticiones HTTP.  Usa {@link ProcedureValidator}
 * para validar los campos.
 */
@Component
public class ProcedureBuilder {
    @Autowired
    private ProcedureValidator validator;

    public Procedure build(String id, String name, String cost) throws Exception {
        Procedure procedure = new Procedure();
        procedure.setId(validator.idValidator(id));
        procedure.setName(validator.nameValidator(name));
        procedure.setCost(validator.costValidator(cost));
        return procedure;
    }
}