package app.domain.services;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

public class CreateEmployee {

	private EmployeePort employeePort;

	public void create(Employee employee) throws Exception {
		if (employeePort.findByDocument(employee) != null) {
			throw new Exception("Ya existe una persona registrada con esa cedula");
		}
		if (employeePort.findByUserName(employee) != null) {
			throw new Exception("Ya existe una persona registrada con ese nombre de usuario");
		}
		employeePort.save(employee);
	}

}