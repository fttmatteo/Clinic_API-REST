package app.domain.ports;

import app.domain.model.Employee;

public interface EmployeePort {

    public Employee findByDocument(int document) throws Exception;
    public Employee findByUserName(String userName) throws Exception;
    public Employee save(Employee employee) throws Exception;
    public void deleteByDocument(int document) throws Exception;
}
