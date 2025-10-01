package app.adapter.in.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.VitalSignsBuilder;
import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.PatientValidator;
import app.adapter.in.validators.VitalSignsValidator;
import app.application.usecase.NurseUseCase;
import app.domain.model.VitalSignsRecord;

/**
 * Cliente de consola para enfermeras.
 * Permite registrar los signos vitales de un paciente de forma interactiva.
 */
@Controller
public class NurseClient {

    private static final String MENU =
        "---------- AREA ENFERMERIA ----------\n" +
        "Ingrese una opcion:\n" +
        "1. Registrar signos vitales\n" +
        "2. SALIR\n";

    private static final String INVALID_OPTION_MESSAGE =
        "Opcion invalida. Por favor elija una opcion del 1 al 2.";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private NurseUseCase nurseUseCase;
    @Autowired
    private VitalSignsBuilder vitalSignsBuilder;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private VitalSignsValidator vitalSignsValidator;

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
                VitalSignsRecord record = readVitalSignsData();
                nurseUseCase.recordVitalSigns(record);
                return true;
            }
            case "2": {
                System.out.println("Cerrando sesion del area enfermeria...");
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

    private VitalSignsRecord readVitalSignsData() throws Exception {
        String nurseDocument = askUntilValid("Ingrese el documento de la enfermera:", value -> {
            employeeValidator.documentValidator(value);
            return value;
        });
        String patientDocument = askUntilValid("Ingrese el documento del paciente:", value -> {
            patientValidator.documentValidator(value);
            return value;
        });
        String bloodPressure = askUntilValid(
            "Ingrese la presion arterial (formato systolic/diastolic, por ejemplo 120/80):",
            vitalSignsValidator::bloodPressureValidator
        );
        String temperature = askUntilValid(
            "Ingrese la temperatura (en grados Celsius):",
            value -> {
                vitalSignsValidator.temperatureValidator(value);
                return value;
            }
        );
        String pulse = askUntilValid(
            "Ingrese el pulso (latidos por minuto):",
            value -> {
                vitalSignsValidator.pulseValidator(value);
                return value;
            }
        );
        String oxygenLevel = askUntilValid(
            "Ingrese el nivel de oxigeno (porcentaje):",
            value -> {
                vitalSignsValidator.oxygenLevelValidator(value);
                return value;
            }
        );
        return vitalSignsBuilder.build(
            nurseDocument,
            patientDocument,
            bloodPressure,
            temperature,
            pulse,
            oxygenLevel
        );
    }

    private String askMenuOption() {
        String[] validOptions = {"1", "2"};
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

    private <T> T askUntilValid(String prompt, CheckedFunction<String, T> mapper) {
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
    private interface CheckedFunction<I, O> {
        O apply(I value) throws Exception;
    }
}
