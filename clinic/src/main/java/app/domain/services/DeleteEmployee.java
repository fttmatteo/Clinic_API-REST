package app.domain.services;


import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class DeleteEmployee {

    private EmployeePort employeePort;

    public void delete(Employee employee) throws Exception {
        if (employeePort.findByDocument(employee) == null) {
            throw new Exception("No existe una persona registrada con esa cedula");
        }
        if (employeePort.findByUserName(employee) == null) {
            throw new Exception("No existe una persona registrada con ese nombre de usuario");
        }
        employeePort.delete(employee);
    }
}
