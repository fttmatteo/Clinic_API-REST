package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.EmployeeBuilder;
import app.application.usecase.HumanResourcesUseCase;
import app.domain.model.Employee;

/**
 * Cliente de consola para el personal de recursos humanos.
 * Permite registrar empleados de distintos roles interactuando
 * por línea de comandos. 
 */
@Controller
public class HumanResourcesClient {

    private static final String MENU =
        "Ingrese una opción:\n" +
        "1. Crear médico\n" +
        "2. Crear enfermera\n" +
        "3. Crear personal administrativo\n" +
        "4. Crear personal de soporte de información\n" +
        "5. Salir";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private HumanResourcesUseCase humanResourcesUseCase;

    @Autowired
    private EmployeeBuilder employeeBuilder;

    public void session() {
        boolean running = true;
        while (running) {
            running = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = reader.nextLine();
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
                System.out.println("Hasta luego. Cerrando sesión.");
                return false;
            }
            default: {
                System.out.println("Opción inválida, por favor intente de nuevo.");
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private Employee readEmployeeData() throws Exception {
        System.out.println("Ingrese el nombre completo:");
        String fullName = reader.nextLine();
        System.out.println("Ingrese el número de documento:");
        String document = reader.nextLine();
        System.out.println("Ingrese la fecha de nacimiento (DD/MM/YYYY):");
        String birthDate = reader.nextLine();
        System.out.println("Ingrese la dirección:");
        String address = reader.nextLine();
        System.out.println("Ingrese el teléfono (máximo 10 dígitos):");
        String phone = reader.nextLine();
        System.out.println("Ingrese el correo electrónico:");
        String email = reader.nextLine();
        System.out.println("Ingrese el nombre de usuario:");
        String userName = reader.nextLine();
        System.out.println("Ingrese la contraseña:");
        String password = reader.nextLine();
        return employeeBuilder.build(fullName, document, birthDate, address, phone, email, userName, password);
    }
}
