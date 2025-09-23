package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.model.enums.Role;
import app.domain.services.CreateEmployee;

@Service
public class EmployeeUseCase {

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

    public void createHR(Employee employee) throws Exception {
        employee.setRole(Role.HUMAN_RESOURCES);
        createEmployee.create(employee);
    }

    public void createIS(Employee employee) throws Exception {
        employee.setRole(Role.INFORMATION_SUPPORT);
        createEmployee.create(employee);
    }
}
