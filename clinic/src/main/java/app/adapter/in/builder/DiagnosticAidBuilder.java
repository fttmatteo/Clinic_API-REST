package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.DiagnosticAidValidator;
import app.domain.model.inventory.DiagnosticAid;

/**
 * Builder para crear instancias de {@link DiagnosticAid} a partir de
 * cadenas de texto provenientes de solicitudes.  Emplea el
 * {@link DiagnosticAidValidator} para validar y convertir los campos.
 */
@Component
public class DiagnosticAidBuilder {
    @Autowired
    private DiagnosticAidValidator validator;

    public DiagnosticAid build(String id, String name, String cost) throws Exception {
        DiagnosticAid aid = new DiagnosticAid();
        aid.setId(validator.idValidator(id));
        aid.setName(validator.nameValidator(name));
        aid.setCost(validator.costValidator(cost));
        return aid;
    }
}