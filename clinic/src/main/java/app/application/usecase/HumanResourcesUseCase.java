package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.domain.services.CreateEmployee;

/**
 * Caso de uso para las funcionalidades de recursos humanos. Permite crear
 * empleados asignando el rol correspondiente antes de delegar la creacion
 * al servicio de dominio.
 */
@Service
public class HumanResourcesUseCase {

    @Autowired
    private CreateEmployee createEmployee;

    public void createDoctor(Employee employee) throws Exception {
        employee.setRole(Role.DOCTOR);
        createEmployee.create(employee);
    }

    public void createNurse(Employee employee) throws Exception {
        employee.setRole(Role.NURSE);
        createEmployee.create(employee);
    }

    public void createAdministrative(Employee employee) throws Exception {
        employee.setRole(Role.PERSONAL_ADMINISTRATIVE);
        createEmployee.create(employee);
    }

    public void createInformationSupport(Employee employee) throws Exception {
        employee.setRole(Role.INFORMATION_SUPPORT);
        createEmployee.create(employee);
    }

    public void documentIsUnique(long document) throws Exception {
        createEmployee.documentIsUnique(document);
    }

    public void userNameIsUnique(String userName) throws Exception {
        createEmployee.userNameIsUnique(userName);
    }
}
