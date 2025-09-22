package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;
import app.infrastructure.persistence.repository.ClinicalHistoryRepository;
import app.infrastructure.persistence.mapper.ClinicalHistoryMapper;
import app.infrastructure.persistence.entities.ClinicalHistoryEntity;

@Service
public class ClinicalHistoryAdapter implements ClinicalHistoryPort {

    private final ClinicalHistoryRepository repository;
    private final ClinicalHistoryMapper mapper;

    public ClinicalHistoryAdapter(ClinicalHistoryRepository repository, ClinicalHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalHistory save(ClinicalHistory clinicalHistory) throws Exception {
        ClinicalHistoryEntity saved = repository.save(mapper.toEntity(clinicalHistory));
        return mapper.toDomain(saved);
    }

    @Override
    public List<ClinicalHistory> findByPatient(int patientDocument) throws Exception {
        return repository.findByPatientDocumentOrderByDateDesc(patientDocument)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
