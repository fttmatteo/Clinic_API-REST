package app.application.usecase;

import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.domain.services.CreateEmployee;

public class HumanResourcesUseCase {

    private CreateEmployee CreateEmployee;
    
    public void createDoctor(Employee user) throws Exception {
        user.setRole(Role.DOCTOR);
        CreateEmployee.create(user);
    }
    public void createNurse(Employee user) throws Exception {
        user.setRole(Role.NURSE);
        CreateEmployee.create(user);
    }
    public void createPersonalAdministrative(Employee user) throws Exception {
        user.setRole(Role.PERSONAL_ADMINISTRATIVE);
        CreateEmployee.create(user);
    }
    public void createInformationSupport(Employee user) throws Exception {
        user.setRole(Role.INFORMATION_SUPPORT);
        CreateEmployee.create(user);
    }
    

}
