package app.adapter.in.validators;

public class EmployeeValidator extends SimpleValidator {

    public String nameValidator(String value) throws Exception {
		return stringValidator("nombre de la persona", value);
	}
	
	public String userNameValidator(String value) throws Exception {
		return stringValidator("nombre del usuario", value);
	}
	
	public String passwordValidator(String value) throws Exception {
		return stringValidator("contraseña", value);
	}
	
	public int documentValidator(String value) throws Exception {
		return integerValidator("el documento de la persona", value);
	}
	
	public int ageValidator(String value) throws Exception {
		return integerValidator("edad de la persona", value);
	}

}
