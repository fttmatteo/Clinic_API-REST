package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

/**
 * Servicio de dominio responsable de crear empleados en la clinica. Aplica
 * reglas de negocio antes de delegar la persistencia al puerto de
 * infraestructura. Verifica que el documento y el nombre de usuario no se
 * encuentren registrados previamente.
 */
@Service
public class CreateEmployee {

    @Autowired
    private EmployeePort employeePort;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public void create(Employee employee) throws Exception {
        documentIsUnique(employee.getDocument());
        userNameIsUnique(employee.getUserName());
        if (employee.getPassword() != null) {
            String encoded = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(encoded);
        }
        employeePort.save(employee);
    }

    public void documentIsUnique(long document) throws Exception {
        Employee probe = new Employee();
        probe.setDocument(document);
        if (employeePort.findByDocument(probe) != null) {
            throw new BusinessException("ya existe una persona registrada con esa cedula");
        }
    }

    public void userNameIsUnique(String userName) throws Exception {
        Employee probe = new Employee();
        probe.setUserName(userName);
        if (employeePort.findByUserName(probe) != null) {
            throw new BusinessException("ya existe una persona registrada con ese nombre de usuario");
        }
    }
}
