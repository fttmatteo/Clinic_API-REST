package app.infrastructure.persistence.mapper;

import app.domain.model.DiagnosticAid;
import app.infrastructure.persistence.entities.DiagnosticAidEntity;

/**
 * Mapper para convertir entre {@link DiagnosticAid} del dominio y
 * {@link DiagnosticAidEntity} de la capa de persistencia.
 */
public class DiagnosticAidMapper {
    public static DiagnosticAidEntity toEntity(DiagnosticAid domain) {
        if (domain == null) return null;
        DiagnosticAidEntity entity = new DiagnosticAidEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCost(domain.getCost());
        entity.setDefaultQuantity(domain.getDefaultQuantity());
        entity.setDefaultRequiresSpecialist(domain.getDefaultRequiresSpecialist());
        return entity;
    }
    public static DiagnosticAid toDomain(DiagnosticAidEntity entity) {
        if (entity == null) return null;
        DiagnosticAid domain = new DiagnosticAid();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCost(entity.getCost());
        domain.setDefaultQuantity(entity.getDefaultQuantity());
        domain.setDefaultRequiresSpecialist(entity.getDefaultRequiresSpecialist());
        return domain;
    }
}
