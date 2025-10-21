package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.VitalSignsRecord;
import app.domain.ports.VitalSignsPort;
import app.infrastructure.persistence.entities.VitalSignsRecordEntity;
import app.infrastructure.persistence.mapper.VitalSignsMapper;
import app.infrastructure.persistence.repository.VitalSignsRepository;

/**
 * Adaptador de infraestructura que implementa el puerto
 * {@link VitalSignsPort}. Permite guardar registros de signos vitales
 * usando JPA.
 */
@Service
public class VitalSignsAdapter implements VitalSignsPort {
    @Autowired
    private VitalSignsRepository vitalSignsRepository;

    @Override
    public void save(VitalSignsRecord record) throws Exception {
        VitalSignsRecordEntity entity = VitalSignsMapper.toEntity(record);
        vitalSignsRepository.save(entity);
        record.setId(entity.getId());
    }
}