package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class EmployeeService {
    private EmployeePort employeePort;

    public EmployeeService(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public void saveEmployee(Employee employee) throws Exception {
        employeePort.save(employee);
    }

    public Employee findEmployeeByDocument(Employee employee) throws Exception {
        return employeePort.findByDocument(employee);
    }
}
