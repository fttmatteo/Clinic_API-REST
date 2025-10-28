package app.adapter.out.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;

/**
 * Adaptador de infraestructura que implementa el puerto {@link PatientPort}.
 * Permite consultar y guardar pacientes usando JPA como mecanismo de
 * persistencia.
 */
@Service
public class PatientAdapter implements PatientPort {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient findByDocument(Patient patient) throws Exception {
        PatientEntity entity = patientRepository.findByDocument(patient.getDocument());
        return PatientMapper.toDomain(entity);
    }

    @Override
    public Patient findById(Patient patient) throws Exception {
        Optional<PatientEntity> opt = patientRepository.findById(patient.getId());
        return opt.map(PatientMapper::toDomain).orElse(null);
    }

    @Override
    public void save(Patient patient) throws Exception {
        PatientEntity entity = PatientMapper.toEntity(patient);
        patientRepository.save(entity);
        patient.setId(entity.getId());
        if (patient.getInsurancePolicy() != null && entity.getInsurancePolicy() != null) {
            patient.getInsurancePolicy().setId(entity.getInsurancePolicy().getId());
        }
    }
}