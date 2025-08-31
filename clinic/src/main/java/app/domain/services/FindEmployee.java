package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class FindEmployee {
    private EmployeePort employeePort;

    public FindEmployee(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public Employee findByDocument(Employee employee) throws Exception {
        Employee found = employeePort.findByDocument(employee);
        if (found == null) {
            throw new Exception("No existe un empleado registrado con esa cédula");
        }
        return found;
    }
}
