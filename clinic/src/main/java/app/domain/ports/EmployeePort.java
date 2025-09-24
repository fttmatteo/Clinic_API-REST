package app.domain.ports;

import app.domain.model.Employee;

public interface EmployeePort {
    Employee findByDocument(Integer document) throws Exception;
    Employee findByUserName(String userName) throws Exception;
    Employee save(Employee employee) throws Exception;
    void deleteByDocument(Integer document) throws Exception;
}
