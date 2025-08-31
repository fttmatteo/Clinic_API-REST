package app.adapter.in.builder;

import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;


public class EmployeeBuilder {

    private EmployeeValidator EmployeeValidator;
	
	
	public Employee build(String name, String document, String age, String userName, String password) throws Exception {
		Employee employee = new Employee();
		employee.setFullName(EmployeeValidator.nameValidator(name));
		employee.setDocument(EmployeeValidator.documentValidator(document));
		employee.setAge(EmployeeValidator.ageValidator(age));
		employee.setUserName(EmployeeValidator.userNameValidator(userName));
		employee.setPassword(EmployeeValidator.passwordValidator(password));
		return employee;
	}
}
