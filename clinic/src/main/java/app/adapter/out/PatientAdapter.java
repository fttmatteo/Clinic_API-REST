package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;
import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.entities.PatientEntity;

@Service
public class PatientAdapter implements PatientPort {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientAdapter(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Patient findByPatient(int patientDocument) throws Exception {
        PatientEntity e = repository.findByDocument(patientDocument);
        return mapper.toDomain(e);
    }

    @Override
    public Patient save(Patient patient) throws Exception {
        PatientEntity saved = repository.save(mapper.toEntity(patient));
        return mapper.toDomain(saved);
    }
}
