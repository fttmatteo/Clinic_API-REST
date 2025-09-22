package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

@Service
public class CreateEmployee {

    @Autowired
    private EmployeePort employeePort;

    public Employee create(Employee employee) throws Exception {
        if (employeePort.findByDocument(employee.getDocument()) != null) {
            throw new BusinessException("ya existe un empleado con esa cédula");
        }
        if (employeePort.findByUserName(employee.getUserName()) != null) {
            throw new BusinessException("ya existe un empleado con ese nombre de usuario");
        }
        return employeePort.save(employee);
    }
}
