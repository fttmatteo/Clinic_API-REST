package app.domain.ports;

import app.domain.model.Employee;

public interface UserPort {
	public Employee findByDocument(Employee user) throws Exception;
	public Employee findByUserName(Employee user) throws Exception;
	public void save(Employee user) throws Exception;
}


