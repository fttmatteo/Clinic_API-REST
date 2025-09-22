package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.ClinicalOrder;
import app.domain.ports.ClinicalOrderPort;
import app.infrastructure.persistence.repository.ClinicalOrderRepository;
import app.infrastructure.persistence.mapper.ClinicalOrderMapper;
import app.infrastructure.persistence.entities.ClinicalOrderEntity;

@Service
public class ClinicalOrderAdapter implements ClinicalOrderPort {

    private final ClinicalOrderRepository repository;
    private final ClinicalOrderMapper mapper;

    public ClinicalOrderAdapter(ClinicalOrderRepository repository, ClinicalOrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalOrder findByOrderId(int numberOrder) throws Exception {
        ClinicalOrderEntity e = repository.findByNumberOrder(numberOrder);
        return mapper.toDomain(e);
    }

    @Override
    public List<ClinicalOrder> findByPatient(int patientDocument) throws Exception {
        return repository.findByPatientDocument(patientDocument)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public ClinicalOrder save(ClinicalOrder clinicalOrder) throws Exception {
        ClinicalOrderEntity saved = repository.save(mapper.toEntity(clinicalOrder));
        return mapper.toDomain(saved);
    }
}
