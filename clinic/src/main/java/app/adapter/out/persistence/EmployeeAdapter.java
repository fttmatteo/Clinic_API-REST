package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.mapper.EmployeeMapper;
import app.infrastructure.persistence.repository.EmployeeRepository;

/**
 * Adaptador de infraestructura que implementa el puerto de persistencia
 * {@link EmployeePort} para empleados. Utiliza Spring Data JPA para
 * acceder a la base de datos y los mapeadores para convertir entre
 * entidades y modelos de dominio.
 */
@Service
public class EmployeeAdapter implements EmployeePort {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findByDocument(Employee employee) throws Exception {
        EmployeeEntity entity = employeeRepository.findByDocument(employee.getDocument());
        return EmployeeMapper.toDomain(entity);
    }

    @Override
    public Employee findByUserName(Employee employee) throws Exception {
        EmployeeEntity entity = employeeRepository.findByUserName(employee.getUserName());
        return EmployeeMapper.toDomain(entity);
    }

    @Override
    public void save(Employee employee) throws Exception {
        EmployeeEntity entity = EmployeeMapper.toEntity(employee);
        employeeRepository.save(entity);
        employee.setId(entity.getId());
    }
}