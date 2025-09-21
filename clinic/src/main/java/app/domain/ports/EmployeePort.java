package app.domain.ports;

import app.domain.model.Employee;

public interface EmployeePort {

    public Employee findByDocument(int document) throws Exception;
    public Employee findByUsername(String username) throws Exception;
    public Employee save(Employee employee) throws Exception;
}
