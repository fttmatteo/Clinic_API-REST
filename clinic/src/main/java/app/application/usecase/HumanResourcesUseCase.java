package app.application.usecase;

import app.domain.model.Employee;
import app.domain.model.emuns.Role;
import app.domain.services.CreateUser;

public class HumanResourcesUseCase {

    private CreateUser CreateUser;
    
    public void createDoctor(Employee user) throws Exception {
        user.setRole(Role.DOCTOR);
        CreateUser.create(user);
    }
    public void createNurse(Employee user) throws Exception {
        user.setRole(Role.NURSE);
        CreateUser.create(user);
    }
    public void createPersonalAdministrative(Employee user) throws Exception {
        user.setRole(Role.PERSONAL_ADMINISTRATIVE);
        CreateUser.create(user);
    }
    public void createInformationSupport(Employee user) throws Exception {
        user.setRole(Role.INFORMATION_SUPPORT);
        CreateUser.create(user);
    }
}