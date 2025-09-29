package app.infrastructure.persistence.mapper;

import app.domain.model.inventory.Medicine;
import app.infrastructure.persistence.entities.MedicineEntity;

/**
 * Mapper para convertir entre {@link Medicine} del dominio y
 * {@link MedicineEntity} de la capa de persistencia.
 */
public class MedicineMapper {
    public static MedicineEntity toEntity(Medicine domain) {
        if (domain == null) return null;
        MedicineEntity entity = new MedicineEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCost(domain.getCost());
        return entity;
    }

    public static Medicine toDomain(MedicineEntity entity) {
        if (entity == null) return null;
        Medicine domain = new Medicine();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCost(entity.getCost());
        return domain;
    }
}