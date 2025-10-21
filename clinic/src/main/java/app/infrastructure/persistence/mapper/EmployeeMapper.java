package app.infrastructure.persistence.mapper;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.infrastructure.persistence.entities.EmployeeEntity;

/**
 * Mapper para convertir entre {@link Employee} del dominio y
 * {@link EmployeeEntity} de la capa de persistencia. Se encarga de
 * trasladar los atributos comunes y convertir el rol entre enum y
 * cadena.
 */
public class EmployeeMapper {
    public static EmployeeEntity toEntity(Employee employee) {
        if (employee == null) return null;
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setFullName(employee.getFullName());
        entity.setDocument(employee.getDocument());
        entity.setBirthDate(employee.getBirthDate());
        entity.setAddress(employee.getAddress());
        entity.setPhone(employee.getPhone());
        entity.setEmail(employee.getEmail());
        entity.setUserName(employee.getUserName());
        entity.setPassword(employee.getPassword());
        if (employee.getRole() != null) {
            entity.setRole(employee.getRole().name());
        }
        return entity;
    }

    public static Employee toDomain(EmployeeEntity entity) {
        if (entity == null) return null;
        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setFullName(entity.getFullName());
        employee.setDocument(entity.getDocument());
        employee.setBirthDate(entity.getBirthDate());
        employee.setAddress(entity.getAddress());
        employee.setPhone(entity.getPhone());
        employee.setEmail(entity.getEmail());
        employee.setUserName(entity.getUserName());
        employee.setPassword(entity.getPassword());
        if (entity.getRole() != null) {
            employee.setRole(Role.valueOf(entity.getRole()));
        }
        return employee;
    }
}