package app.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.ports.DiagnosticAidPort;
import app.infrastructure.persistence.entities.DiagnosticAidEntity;
import app.infrastructure.persistence.mapper.DiagnosticAidMapper;
import app.infrastructure.persistence.repository.DiagnosticAidRepository;

/**
 * Adaptador de infraestructura que implementa el puerto
 * {@link DiagnosticAidPort}.  Se encarga de la persistencia de las
 * ayudas diagnósticas mediante JPA.
 */
@Service
public class DiagnosticAidAdapter implements DiagnosticAidPort {
    @Autowired
    private DiagnosticAidRepository repository;

    @Override
    public void save(DiagnosticAid aid) throws Exception {
        DiagnosticAidEntity entity = DiagnosticAidMapper.toEntity(aid);
        repository.save(entity);
    }

    @Override
    public DiagnosticAid findById(String id) throws Exception {
        Optional<DiagnosticAidEntity> opt = repository.findById(id);
        return opt.map(DiagnosticAidMapper::toDomain).orElse(null);
    }

    @Override
    public List<DiagnosticAid> findAll() throws Exception {
        List<DiagnosticAid> list = new ArrayList<>();
        for (DiagnosticAidEntity entity : repository.findAll()) {
            list.add(DiagnosticAidMapper.toDomain(entity));
        }
        return list;
    }
}