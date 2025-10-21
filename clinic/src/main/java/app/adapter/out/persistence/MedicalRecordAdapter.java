package app.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.ports.MedicalRecordPort;
import app.infrastructure.persistence.entities.MedicalRecordEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.MedicalRecordMapper;
import app.infrastructure.persistence.repository.MedicalRecordRepository;

/**
 * Adaptador que implementa el puerto {@link MedicalRecordPort}. Utiliza
 * un repositorio JPA para guardar y consultar historias clínicas.
 */
@Service
public class MedicalRecordAdapter implements MedicalRecordPort {
    @Autowired
    private MedicalRecordRepository recordRepository;

    @Override
    public void save(MedicalRecord record) throws Exception {
        MedicalRecordEntity entity = MedicalRecordMapper.toEntity(record);
        recordRepository.save(entity);
        record.setId(entity.getId());
    }

    @Override
    public List<MedicalRecord> findByPatient(Patient patient) throws Exception {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        List<MedicalRecordEntity> entities = recordRepository.findByPatient(patientEntity);
        List<MedicalRecord> records = new ArrayList<>();
        for (MedicalRecordEntity entity : entities) {
            records.add(MedicalRecordMapper.toDomain(entity));
        }
        return records;
    }
}