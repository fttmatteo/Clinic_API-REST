package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class EditEmployee {
    private EmployeePort employeePort;

    public EditEmployee(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public void edit(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("El objeto empleado es nulo");
        }
        if (employee.getUsername() == null || employee.getUsername().isEmpty()) {
            throw new Exception("El nombre del empleado es obligatorio");
        }
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new Exception("El correo electrónico es obligatorio");
        }

        Employee existingEmployee = employeePort.findByDocument(employee);
        if (existingEmployee == null) {
            throw new Exception("El empleado no existe");
        }

        existingEmployee.setUsername(employee.getUsername());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());

        employeePort.save(existingEmployee);
        employeePort.save(employee);
    }
}
