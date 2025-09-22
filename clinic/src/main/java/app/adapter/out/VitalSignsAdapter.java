package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.VitalSigns;
import app.domain.ports.VitalSignsPort;
import app.infrastructure.persistence.repository.VitalSignsRepository;
import app.infrastructure.persistence.mapper.VitalSignsMapper;
import app.infrastructure.persistence.entities.VitalSignsEntity;

@Service
public class VitalSignsAdapter implements VitalSignsPort {

    private final VitalSignsRepository repository;
    private final VitalSignsMapper mapper;

    public VitalSignsAdapter(VitalSignsRepository repository, VitalSignsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VitalSigns save(VitalSigns vitalSigns) throws Exception {
        VitalSignsEntity saved = repository.save(mapper.toEntity(vitalSigns));
        return mapper.toDomain(saved);
    }

    @Override
    public List<VitalSigns> listByPatient(int patientDocument) throws Exception {
        return repository.findById_PatientDocumentOrderById_DateAsc(patientDocument)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
