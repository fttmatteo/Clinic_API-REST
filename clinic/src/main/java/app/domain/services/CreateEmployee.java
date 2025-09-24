package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Employee;
import app.domain.ports.EmployeePort;

@Service
public class CreateEmployee {
	
	@Autowired
	private EmployeePort employeePort;

	public Employee execute(Employee employee) throws Exception {
		if (employee == null) throw new Exception("El empleado es obligatorio");
		if (employee.getDocument() == null) throw new Exception("La cédula del empleado es obligatoria");
		if (employee.getUserName() == null || employee.getUserName().isBlank()) {
			throw new Exception("El nombre de usuario es obligatorio");
		}
		if (employeePort.findByDocument(employee.getDocument()) != null) {
			throw new Exception("Ya existe un empleado con esa cédula");
		}
		if (employeePort.findByUserName(employee.getUserName()) != null) {
			throw new Exception("El nombre de usuario ya existe");
		}
		return employeePort.save(employee);
	}
}
