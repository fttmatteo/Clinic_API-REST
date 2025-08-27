package app.application.usecase;

import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.domain.services.CreateEmployee;

public class HumanResourcesUseCase {

    private CreateEmployee CreateEmployee;
    
    public void createDoctor(Employee employee) throws Exception {
        employee.setRole(Role.DOCTOR);
        CreateEmployee.create(employee);
    }
    public void createNurse(Employee employee) throws Exception {
        employee.setRole(Role.NURSE);
        CreateEmployee.create(employee);
    }
    public void createPersonalAdministrative(Employee employee) throws Exception {
        employee.setRole(Role.PERSONAL_ADMINISTRATIVE);
        CreateEmployee.create(employee);
    }
    public void createInformationSupport(Employee employee) throws Exception {
        employee.setRole(Role.INFORMATION_SUPPORT);
        CreateEmployee.create(employee);
    }
}