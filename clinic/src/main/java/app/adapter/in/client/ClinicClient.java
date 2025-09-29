package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Cliente principal de consola para la clínica. Permite seleccionar el rol
 * con el que se desea operar y delega la sesión al cliente específico de
 * cada área (recursos humanos, administrativo, médico o enfermería).
 */
@Controller
public class ClinicClient {

    private static final String MENU =
        "---------- MENÚ PRINCIPAL ----------\n" +
        "Seleccione el área con la que desea operar:\n" +
        "1. Recursos Humanos\n" +
        "2. Administrativo\n" +
        "3. Médico\n" +
        "4. Enfermería\n" +
        "5. SALIR\n";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private HumanResourcesClient humanResourcesClient;
    @Autowired
    private AdministrativeClient administrativeClient;
    @Autowired
    private DoctorClient doctorClient;
    @Autowired
    private NurseClient nurseClient;

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
                humanResourcesClient.session();
                return true;
            }
            case "2": {
                administrativeClient.session();
                return true;
            }
            case "3": {
                doctorClient.session();
                return true;
            }
            case "4": {
                nurseClient.session();
                return true;
            }
            case "5": {
                System.out.println("Hasta luego. Cerrando sesión.");
                return false;
            }
            default: {
                System.out.println("Opción inválida. Por favor elija una opción del 1 al 5.");
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}