package app.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Procedure;
import app.domain.ports.ProcedurePort;
import app.infrastructure.persistence.entities.ProcedureEntity;
import app.infrastructure.persistence.mapper.ProcedureMapper;
import app.infrastructure.persistence.repository.ProcedureRepository;

/**
 * Adaptador de infraestructura que implementa el puerto
 * {@link ProcedurePort}.  Gestiona la persistencia de procedimientos
 * médicos utilizando JPA.
 */
@Service
public class ProcedureAdapter implements ProcedurePort {
    @Autowired
    private ProcedureRepository repository;

    @Override
    public void save(Procedure procedure) throws Exception {
        ProcedureEntity entity = ProcedureMapper.toEntity(procedure);
        repository.save(entity);
    }

    @Override
    public Procedure findById(String id) throws Exception {
        Optional<ProcedureEntity> opt = repository.findById(id);
        return opt.map(ProcedureMapper::toDomain).orElse(null);
    }

    @Override
    public List<Procedure> findAll() throws Exception {
        List<Procedure> list = new ArrayList<>();
        for (ProcedureEntity entity : repository.findAll()) {
            list.add(ProcedureMapper.toDomain(entity));
        }
        return list;
    }
}