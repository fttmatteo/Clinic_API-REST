package app.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;
import app.infrastructure.persistence.entities.MedicineEntity;
import app.infrastructure.persistence.mapper.MedicineMapper;
import app.infrastructure.persistence.repository.MedicineRepository;

/**
 * Adaptador de infraestructura que implementa el puerto
 * {@link MedicinePort}.  Proporciona operaciones para guardar y
 * recuperar medicamentos usando JPA.
 */
@Service
public class MedicineAdapter implements MedicinePort {
    @Autowired
    private MedicineRepository repository;

    @Override
    public void save(Medicine medicine) throws Exception {
        MedicineEntity entity = MedicineMapper.toEntity(medicine);
        repository.save(entity);
    }

    @Override
    public Medicine findById(String id) throws Exception {
        Optional<MedicineEntity> opt = repository.findById(id);
        return opt.map(MedicineMapper::toDomain).orElse(null);
    }

    @Override
    public List<Medicine> findAll() throws Exception {
        List<Medicine> list = new ArrayList<>();
        for (MedicineEntity entity : repository.findAll()) {
            list.add(MedicineMapper.toDomain(entity));
        }
        return list;
    }
}