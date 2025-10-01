package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.EmployeeBuilder;
import app.adapter.in.validators.EmployeeValidator;
import app.application.usecase.HumanResourcesUseCase;
import app.domain.model.Employee;

/**
 * Cliente de consola para el personal de recursos humanos.
 * Permite registrar empleados de distintos roles interactuando
 * por linea de comandos.
 */
@Controller
public class HumanResourcesClient {

    private static final String MENU =
        "---------- AREA RECURSOS HUMANOS ----------\n" +
        "Ingrese una opcion:\n" +
        "1. Crear medico\n" +
        "2. Crear enfermera\n" +
        "3. Crear personal administrativo\n" +
        "4. Crear personal de soporte de informacion\n" +
        "5. SALIR\n";

    private static final String INVALID_OPTION_MESSAGE =
        "Opcion invalida. Por favor elija una opcion del 1 al 5.\n";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private HumanResourcesUseCase humanResourcesUseCase;

    @Autowired
    private EmployeeBuilder employeeBuilder;

    @Autowired
    private EmployeeValidator employeeValidator;

    public void session() {
        boolean running = true;
        while (running) {
            running = menu();
        }
    }

    private boolean menu() {
        try {
            String option = menuOption();
            switch (option) {
            case "1": {
                Employee employee = readEmployeeData();
                humanResourcesUseCase.createDoctor(employee);
                return true;
            }
            case "2": {
                Employee employee = readEmployeeData();
                humanResourcesUseCase.createNurse(employee);
                return true;
            }
            case "3": {
                Employee employee = readEmployeeData();
                humanResourcesUseCase.createAdministrative(employee);
                return true;
            }
            case "4": {
                Employee employee = readEmployeeData();
                humanResourcesUseCase.createInformationSupport(employee);
                return true;
            }
            case "5": {
                System.out.println("Cerrando sesion del area recursos humanos...\n");
                return false;
            }
            default: {
                System.out.println(INVALID_OPTION_MESSAGE);
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private Employee readEmployeeData() throws Exception {
        String fullName = promptValidator("Ingrese el nombre completo:", employeeValidator::fullNameValidator);
        String document = promptValidator("Ingrese el numero de documento:", value -> {
            long parsed = employeeValidator.documentValidator(value);
            humanResourcesUseCase.ensureDocumentIsUnique(parsed);
            return value;
        });
        String birthDate = promptValidator("Ingrese la fecha de nacimiento (DD/MM/YYYY):", value -> {
            employeeValidator.birthDateValidator(value);
            return value;
        });
        String address = promptValidator("Ingrese la direccion:", employeeValidator::addressValidator);
        String phone = promptValidator("Ingrese el telefono (maximo 10 digitos):", employeeValidator::phoneValidator);
        String email = promptValidator("Ingrese el correo electronico:", employeeValidator::emailValidator);
        String userName = promptValidator("Ingrese el nombre de usuario:", value -> {
            String validated = employeeValidator.userNameValidator(value);
            humanResourcesUseCase.ensureUserNameIsUnique(validated);
            return validated;
        });
        String password = promptValidator("Ingrese la contraseña:", employeeValidator::passwordValidator);
        return employeeBuilder.build(fullName, document, birthDate, address, phone, email, userName, password);
    }

    private String menuOption() {
        String[] validOptions = {"1", "2", "3", "4", "5"};
        while (true) {
            System.out.println(MENU);
            String input = reader.nextLine();
            String value = input == null ? "" : input.trim();
            for (String option : validOptions) {
                if (option.equals(value)) {
                    return option;
                }
            }
            System.out.println(INVALID_OPTION_MESSAGE);
        }
    }

    private <DataType> DataType promptValidator(String prompt, CheckedFunction<String, DataType> mapper) {
        while (true) {
            System.out.println(prompt);
            String input = reader.nextLine();
            String value = input == null ? "" : input.trim();
            try {
                return mapper.apply(value);
            } catch (Exception ex) {
                System.out.println("Dato invalido: " + ex.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface CheckedFunction<Input, Output> {
        Output apply(Input value) throws Exception;
    }
}
