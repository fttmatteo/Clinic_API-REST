package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;

/**
 * Construye instancias de {@link Employee} a partir de cadenas de texto
 * recibidas en peticiones HTTP. Utiliza {@link EmployeeValidator} para
 * validar y convertir los valores.
 */
@Component
public class EmployeeBuilder {
    @Autowired
    private EmployeeValidator validator;
    public Employee build(String fullName, String document, String birthDate, String address,
                          String phone, String email, String userName, String password) throws Exception {
        Employee employee = new Employee();
        employee.setFullName(validator.fullNameValidator(fullName));
        employee.setDocument(validator.documentValidator(document));
        employee.setBirthDate(validator.birthDateValidator(birthDate));
        employee.setAddress(validator.addressValidator(address));
        employee.setPhone(validator.phoneValidator(phone));
        employee.setEmail(validator.emailValidator(email));
        employee.setUserName(validator.userNameValidator(userName));
        employee.setPassword(validator.passwordValidator(password));
        return employee;
    }
}