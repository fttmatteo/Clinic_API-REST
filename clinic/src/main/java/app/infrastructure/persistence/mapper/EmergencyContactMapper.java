package app.infrastructure.persistence.mapper;

import app.domain.model.EmergencyContact;
import app.infrastructure.persistence.entities.EmergencyContactEntity;

/**
 * Mapper para convertir entre la entidad {@link EmergencyContactEntity}
 * y el modelo de dominio {@link EmergencyContact}.
 */
public class EmergencyContactMapper {
    private EmergencyContactMapper() {}

    public static EmergencyContactEntity toEntity(EmergencyContact contact) {
        if (contact == null) return null;
        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setId(contact.getId() == 0 ? null : contact.getId());
        entity.setFirstName(contact.getFirstName());
        entity.setLastName(contact.getLastName());
        entity.setRelationship(contact.getRelationship());
        entity.setPhone(contact.getPhone());
        return entity;
    }

    public static EmergencyContact toDomain(EmergencyContactEntity entity) {
        if (entity == null) return null;
        EmergencyContact contact = new EmergencyContact();
        if (entity.getId() != null) {
            contact.setId(entity.getId());
        }
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setRelationship(entity.getRelationship());
        contact.setPhone(entity.getPhone());
        return contact;
    }
}