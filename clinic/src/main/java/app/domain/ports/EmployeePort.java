package app.domain.ports;

import app.domain.model.Employee;

public interface EmployeePort {
	public Employee findByDocument(Employee employee) throws Exception;
	public Employee findByUserName(Employee employee) throws Exception;
	public void save(Employee employee) throws Exception;
	public void update(Employee employee) throws Exception;
	public void delete(Employee employee) throws Exception;
}


