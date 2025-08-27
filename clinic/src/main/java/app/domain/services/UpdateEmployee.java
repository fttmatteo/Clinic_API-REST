package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.UserPort;

public class UpdateEmployee {

    private UserPort userPort;

    public UpdateEmployee(UserPort userPort) {
        this.userPort = userPort;
    }

    public void update(Employee user) throws Exception {
        Employee existing = userPort.findByDocument(user);
        if (existing == null) {
            throw new Exception("No existe una persona registrada con esa cedula");
        }

        Employee userByUsername = userPort.findByUserName(user);
        if (userByUsername != null && userByUsername.getDocument() != user.getDocument()) {
            throw new Exception("Ya existe una persona registrada con ese nombre de usuario");
        }

        userPort.update(user);
    }
}