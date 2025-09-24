package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.ports.EmployeePort;

@Service
public class DeleteEmployee {
	
	@Autowired
	private EmployeePort employeePort;

	public void execute(Integer document) throws Exception {
		if (document == null) throw new Exception("La cédula es obligatoria");
		if (employeePort.findByDocument(document) == null) {
			throw new Exception("No existe un empleado con esa cédula");
		}
		employeePort.deleteByDocument(document);
	}
}
