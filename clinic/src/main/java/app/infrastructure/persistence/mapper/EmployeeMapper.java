// EmployeeMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Employee;
import app.infrastructure.persistence.entities.EmployeeEntity;

@Component
public class EmployeeMapper {
  public Employee toDomain(EmployeeEntity e){
    if(e==null) return null;
    Employee d = new Employee();
    d.setDocument(e.getDocument());
    d.setFullName(e.getFullName());
    d.setBirthDate(e.getBirthDate());
    d.setAddress(e.getAddress());
    d.setPhoneNumber(e.getPhoneNumber());
    d.setEmail(e.getEmail());
    d.setRole(e.getRole());
    d.setUserName(e.getUserName());
    d.setPassword(e.getPassword());
    return d;
  }
  public EmployeeEntity toEntity(Employee d){
    EmployeeEntity e = new EmployeeEntity();
    e.setDocument(d.getDocument());
    e.setFullName(d.getFullName());
    e.setBirthDate(d.getBirthDate());
    e.setAddress(d.getAddress());
    e.setPhoneNumber(d.getPhoneNumber());
    e.setEmail(d.getEmail());
    e.setRole(d.getRole());
    e.setUserName(d.getUserName());
    e.setPassword(d.getPassword());
    return e;
  }
}
