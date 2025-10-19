package app.infrastructure.persistence.mapper;

import app.domain.model.Procedure;
import app.infrastructure.persistence.entities.ProcedureEntity;

/**
 * Mapper para convertir entre {@link Procedure} del dominio y
 * {@link ProcedureEntity} de la capa de persistencia.
 */
public class ProcedureMapper {
    public static ProcedureEntity toEntity(Procedure domain) {
        if (domain == null) return null;
        ProcedureEntity entity = new ProcedureEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCost(domain.getCost());
        return entity;
    }

    public static Procedure toDomain(ProcedureEntity entity) {
        if (entity == null) return null;
        Procedure domain = new Procedure();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCost(entity.getCost());
        return domain;
    }
}