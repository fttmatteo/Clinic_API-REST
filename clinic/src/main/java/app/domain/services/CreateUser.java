package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.UserPort;

public class CreateUser {

	private UserPort userPort;

	public void create(Employee user) throws Exception {
		if (userPort.findByDocument(user) != null) {
			throw new Exception("ya existe una persona registrada con esa cedula");
		}

		if (userPort.findByUserName(user) != null) {
			throw new Exception("ya existe una persona registrada con ese nombre de usuario");
		}
		userPort.save(user);
	}

}