package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;

@Component
public class EmployeeBuilder {

    @Autowired
    private EmployeeValidator validator;

    public Employee build(
        String fullName,
        String document,
        String email,
        String phoneNumber,
        String birthDate,
        String address,
        String userName,
        String password
    ) throws Exception {

        Employee employee = new Employee();
        employee.setFullName(validator.fullNameValidator(fullName));
        employee.setDocument(validator.documentValidator(document));
        employee.setEmail(validator.emailValidatorField(email));
        employee.setPhoneNumber(validator.phoneNumberValidator(phoneNumber));
        employee.setBirthDate(validator.birthDateValidator(birthDate));
        employee.setAddress(validator.addressValidator(address));
        employee.setUserName(validator.userNameValidator(userName));
        employee.setPassword(validator.passwordValidator(password));
        return employee;
    }
}
