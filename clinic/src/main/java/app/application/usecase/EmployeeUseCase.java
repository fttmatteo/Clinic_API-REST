package app.application.usecase;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class EmployeeUseCase {
    private EmployeePort employeePort;

    public EmployeeUseCase(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public void saveEmployee(Employee employee) throws Exception {
        employeePort.save(employee);
    }

    public Employee findEmployeeByDocument(Employee employee) throws Exception {
        return employeePort.findByDocument(employee);
    }
}
