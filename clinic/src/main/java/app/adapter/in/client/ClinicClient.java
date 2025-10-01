package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Cliente principal de consola para la clinica. Permite seleccionar el rol
 * con el que se desea operar y delega la sesion al cliente especifico de
 * cada area (recursos humanos, administrativo, medico o enfermeria).
 */
@Controller
public class ClinicClient {

    private static final String MENU =
        "---------- CLINICA ----------\n" +
        "Seleccione el area con la que desea operar:\n" +
        "1. Recursos Humanos\n" +
        "2. Administrativo\n" +
        "3. Medico\n" +
        "4. Enfermeria\n" +
        "5. SALIR\n";

    private static final String INVALID_OPTION_MESSAGE =
        "Opcion invalida. Por favor elija una opcion del 1 al 5.";

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
            String option = askMenuOption();
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
                System.out.println("Cerrando sesion principal...");
                return false;
            }
            default: {
                // Nunca deberiamos llegar aqui porque askMenuOption garantiza un valor valido.
                System.out.println(INVALID_OPTION_MESSAGE);
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private String askMenuOption() {
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
}
