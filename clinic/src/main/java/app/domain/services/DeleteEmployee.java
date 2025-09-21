package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.ports.EmployeePort;

@Service
public class DeleteEmployee {

    @Autowired
    private EmployeePort employeePort;

    public void deleteByDocument(int document) throws Exception {
        if (employeePort.findByDocument(document) == null) {
            throw new BusinessException("no existe un empleado con esa cédula");
        }
        employeePort.deleteByDocument(document);
    }
}
