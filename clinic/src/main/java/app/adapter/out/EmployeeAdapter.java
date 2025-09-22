package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;
import app.infrastructure.persistence.repository.EmployeeRepository;
import app.infrastructure.persistence.mapper.EmployeeMapper;
import app.infrastructure.persistence.entities.EmployeeEntity;

@Service
public class EmployeeAdapter implements EmployeePort {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeAdapter(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Employee findByDocument(int document) throws Exception {
        return mapper.toDomain(repository.findByDocument(document));
    }

    @Override
    public Employee findByUserName(String userName) throws Exception {
        return mapper.toDomain(repository.findByUsername(userName));
    }

    @Override
    public Employee save(Employee employee) throws Exception {
        EmployeeEntity saved = repository.save(mapper.toEntity(employee));
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByDocument(int document) throws Exception {
        repository.deleteByDocument(document);
    }
}
