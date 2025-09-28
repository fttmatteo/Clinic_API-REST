package app.infrastructure.persistence.mapper;

import app.domain.model.InsurancePolicy;
import app.infrastructure.persistence.entities.InsurancePolicyEntity;

/**
 * Mapper para convertir entre {@link InsurancePolicyEntity} y
 * {@link InsurancePolicy} en el modelo de dominio.
 */
public class InsurancePolicyMapper {
    private InsurancePolicyMapper() {}

    public static InsurancePolicyEntity toEntity(InsurancePolicy policy) {
        if (policy == null) return null;
        InsurancePolicyEntity entity = new InsurancePolicyEntity();
        entity.setId(policy.getId() == 0 ? null : policy.getId());
        entity.setCompanyName(policy.getCompanyName());
        entity.setPolicyNumber(policy.getPolicyNumber());
        entity.setActive(policy.isActive());
        entity.setExpiryDate(policy.getExpiryDate());
        return entity;
    }

    public static InsurancePolicy toDomain(InsurancePolicyEntity entity) {
        if (entity == null) return null;
        InsurancePolicy policy = new InsurancePolicy();
        if (entity.getId() != null) {
            policy.setId(entity.getId());
        }
        policy.setCompanyName(entity.getCompanyName());
        policy.setPolicyNumber(entity.getPolicyNumber());
        policy.setActive(entity.isActive());
        policy.setExpiryDate(entity.getExpiryDate());
        return policy;
    }
}