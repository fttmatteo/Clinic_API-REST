package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class CreateEmployee {

	private EmployeePort employeePort;

	public void create(Employee user) throws Exception {
		if (employeePort.findByDocument(user) != null) {
			throw new Exception("ya existe una persona registrada con esa cedula");
		}

		if (employeePort.findByUserName(user) != null) {
			throw new Exception("ya existe una persona registrada con ese nombre de usuario");
		}
		employeePort.save(user);
	}

}