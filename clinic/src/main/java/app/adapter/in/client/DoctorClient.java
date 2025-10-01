package app.adapter.in.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.MedicalOrderBuilder;
import app.adapter.in.builder.MedicalRecordBuilder;
import app.adapter.in.builder.OrderItemBuilder;
import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.MedicalOrderValidator;
import app.adapter.in.validators.MedicalRecordValidator;
import app.adapter.in.validators.OrderItemValidator;
import app.adapter.in.validators.PatientValidator;
import app.application.exceptions.InputsException;
import app.application.usecase.DoctorUseCase;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.OrderItem;
import app.domain.model.Patient;
import app.domain.model.enums.OrderItemType;
import app.domain.services.InventoryService;

/**
 * Cliente de consola para medicos.
 * Permite crear ordenes medicas e historias clinicas y consultar las
 * ordenes asociadas a un paciente.
 */
@Controller
public class DoctorClient {

    private static final String MENU =
        "---------- AREA MEDICA ----------\n" +
        "Ingrese una opcion:\n" +
        "1. Crear orden medica\n" +
        "2. Registrar historia clinica\n" +
        "3. Consultar ordenes de un paciente\n" +
        "4. SALIR\n";

    private static final String INVALID_OPTION_MESSAGE =
        "Opcion invalida. Por favor elija una opcion del 1 al 4.\n";

    private static final String YES_NO_ERROR = "Debe responder si o no.\n";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private DoctorUseCase doctorUseCase;
    @Autowired
    private MedicalOrderBuilder orderBuilder;
    @Autowired
    private OrderItemBuilder orderItemBuilder;
    @Autowired
    private MedicalRecordBuilder recordBuilder;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private OrderItemValidator orderItemValidator;
    @Autowired
    private MedicalRecordValidator medicalRecordValidator;
    @Autowired
    private MedicalOrderValidator medicalOrderValidator;
    @Autowired
    private InventoryService inventoryService;

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
                MedicalOrder order = readOrderData();
                doctorUseCase.createMedicalOrder(order);
                return true;
            }
            case "2": {
                MedicalRecord record = readRecordData();
                doctorUseCase.createMedicalRecord(record);
                return true;
            }
            case "3": {
                readAndPrintOrders();
                return true;
            }
            case "4": {
                System.out.println("Cerrando sesion del area medica...\n");
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

    private MedicalOrder readOrderData() throws Exception {
        String doctorDocument = promptValidator("Ingrese el documento del medico que genera la orden:", value -> {
            employeeValidator.documentValidator(value);
            return value;
        });
        String patientDocument = promptValidator("Ingrese el documento del paciente:", value -> {
            patientValidator.documentValidator(value);
            return value;
        });

        boolean useDefault = askYesNo("Desea usar la lista de items predeterminada? (si/no):");
        List<OrderItem> items;
        if (useDefault) {
            items = createDefaultOrderItems();
        } else {
            int count = promptValidator("Ingrese la cantidad de items que tendra la orden:", value -> {
                try {
                    int parsed = Integer.parseInt(value);
                    if (parsed < 1) {
                        throw new InputsException("la orden debe contener al menos un item");
                    }
                    return parsed;
                } catch (NumberFormatException ex) {
                    throw new InputsException("la cantidad de items debe ser un numero entero");
                }
            });
            items = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                System.out.println("Ingrese los datos para el item #" + i);
                OrderItem item = readOrderItemData(i);
                items.add(item);
            }
        }
        return orderBuilder.build(doctorDocument, patientDocument, items);
    }

    private OrderItem readOrderItemData(int index) throws Exception {
        int itemNumber = promptValidator("Numero de item para el registro #" + index + ":", orderItemValidator::itemNumberValidator);
        OrderItemType type = promptValidator(
            "Tipo de item para el registro #" + index + " (MEDICINE, PROCEDURE, DIAGNOSTIC_AID):",
            orderItemValidator::typeValidator
        );
        String name = promptValidator("Nombre del item para el registro #" + index + ":", orderItemValidator::nameValidator);

        String dose = null;
        String treatmentDuration = null;
        if (type == OrderItemType.MEDICINE) {
            dose = promptValidator("Dosis del medicamento:", value -> orderItemValidator.doseValidator(value, type));
            treatmentDuration = promptValidator(
                "Duracion del tratamiento:",
                value -> orderItemValidator.treatmentDurationValidator(value, type)
            );
        }

        Integer quantityValue = null;
        if (type == OrderItemType.PROCEDURE || type == OrderItemType.DIAGNOSTIC_AID) {
            quantityValue = promptValidator("Cantidad:", value -> orderItemValidator.quantityValidator(value, type));
        }
        String quantity = quantityValue == null ? null : Integer.toString(quantityValue);

        String frequency = null;
        if (type == OrderItemType.PROCEDURE) {
            frequency = promptValidator("Frecuencia del procedimiento:", value -> orderItemValidator.frequencyValidator(value, type));
        }

        Double costValue = promptValidator("Ingrese el costo:", orderItemValidator::costValidator);
        String cost = costValue == null ? null : Double.toString(costValue);

        Boolean requiresSpecialistValue = null;
        String requiresSpecialist = null;
        if (type == OrderItemType.PROCEDURE || type == OrderItemType.DIAGNOSTIC_AID) {
            requiresSpecialistValue = promptValidator(
                "Requiere especialista? (si/no):",
                value -> orderItemValidator.requiresSpecialistValidator(value, type)
            );
            if (requiresSpecialistValue != null) {
                requiresSpecialist = requiresSpecialistValue ? "si" : "no";
            }
        }

        String specialistTypeId = null;
        if (Boolean.TRUE.equals(requiresSpecialistValue)) {
            specialistTypeId = promptValidator(
                "Ingrese el id del tipo de especialidad:",
                value -> orderItemValidator.specialistTypeIdValidator(value, type, true)
            );
        }

        return orderItemBuilder.build(
            Integer.toString(itemNumber),
            type.name(),
            name,
            dose,
            treatmentDuration,
            quantity,
            frequency,
            cost,
            requiresSpecialist,
            specialistTypeId
        );
    }

    private MedicalRecord readRecordData() throws Exception {
        String doctorDocument = promptValidator("Ingrese el documento del medico que registra la historia:", value -> {
            employeeValidator.documentValidator(value);
            return value;
        });
        String patientDocument = promptValidator("Ingrese el documento del paciente:", value -> {
            patientValidator.documentValidator(value);
            return value;
        });
        String orderId = askOptional(
            "Ingrese el identificador de la orden asociada (dejar en blanco si no aplica):",
            value -> medicalOrderValidator.idValidator(value)
        );
        String motive = promptValidator("Ingrese el motivo de la consulta:", value -> medicalRecordValidator.motiveValidator(value));
        String symptoms = promptValidator("Ingrese los sintomas reportados:", value -> medicalRecordValidator.symptomsValidator(value));
        String diagnosis = promptValidator("Ingrese el diagnostico:", value -> medicalRecordValidator.diagnosisValidator(value));
        return recordBuilder.build(
            doctorDocument,
            patientDocument,
            orderId,
            motive,
            symptoms,
            diagnosis
        );
    }

    private void readAndPrintOrders() throws Exception {
        long document = promptValidator(
            "Ingrese el documento del paciente para consultar sus ordenes:",
            patientValidator::documentValidator
        );
        Patient patient = new Patient();
        patient.setDocument(document);
        List<MedicalOrder> orders = doctorUseCase.searchMedicalOrders(patient);
        if (orders == null || orders.isEmpty()) {
            System.out.println("No se encontraron ordenes para el paciente especificado.");
            return;
        }
        printOrders(orders);
    }

    private void printOrders(List<MedicalOrder> orders) {
        for (MedicalOrder order : orders) {
            System.out.println("Orden #" + order.getId());
            if (order.getCreationDate() != null) {
                System.out.println("Fecha: " + order.getCreationDate());
            }
            if (order.getDoctor() != null) {
                System.out.println("Medico: " + order.getDoctor().getDocument());
            }
            if (order.getPatient() != null) {
                System.out.println("Paciente: " + order.getPatient().getDocument());
            }
            if (order.getItems() != null && !order.getItems().isEmpty()) {
                System.out.println("Items:");
                order.getItems().forEach(item -> {
                    System.out.println("  Item #" + item.getItemNumber() +
                                       " | Tipo: " + item.getType() +
                                       " | Nombre: " + item.getName() +
                                       " | Costo: " + item.getCost());
                });
            }
            System.out.println("-----------------------------------");
        }
    }

    private List<OrderItem> createDefaultOrderItems() throws Exception {
        List<OrderItem> defaultItems = new ArrayList<>();
        if (!inventoryService.getMedicines().isEmpty()) {
            var med = inventoryService.getMedicines().get(0);
            defaultItems.add(orderItemBuilder.build(
                "1",
                "MEDICINE",
                med.getName(),
                "500 mg",
                "5 dias",
                null,
                null,
                String.valueOf(med.getCost()),
                null,
                null
            ));
        }
        if (!inventoryService.getProcedures().isEmpty()) {
            var proc = inventoryService.getProcedures().get(0);
            defaultItems.add(orderItemBuilder.build(
                String.valueOf(defaultItems.size() + 1),
                "PROCEDURE",
                proc.getName(),
                null,
                null,
                "1",
                "unico",
                String.valueOf(proc.getCost()),
                "si",
                "101"
            ));
        }
        if (!inventoryService.getDiagnosticAids().isEmpty()) {
            var diag = inventoryService.getDiagnosticAids().get(0);
            defaultItems.add(orderItemBuilder.build(
                String.valueOf(defaultItems.size() + 1),
                "DIAGNOSTIC_AID",
                diag.getName(),
                null,
                null,
                "1",
                null,
                String.valueOf(diag.getCost()),
                "no",
                null
            ));
        }
        return defaultItems;
    }

    private String menuOption() {
        String[] validOptions = {"1", "2", "3", "4"};
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

    private boolean askYesNo(String prompt) {
        return promptValidator(prompt, value -> {
            String lower = value.toLowerCase();
            if (lower.equals("si") || lower.equals("s") || lower.equals("true")) {
                return true;
            }
            if (lower.equals("no") || lower.equals("n") || lower.equals("false")) {
                return false;
            }
            throw new InputsException(YES_NO_ERROR);
        });
    }

    private String askOptional(String prompt, CheckedFunction<String, ?> validator) {
        while (true) {
            System.out.println(prompt);
            String input = reader.nextLine();
            String value = input == null ? "" : input.trim();
            if (value.isEmpty()) {
                return "";
            }
            try {
                validator.apply(value);
                return value;
            } catch (Exception ex) {
                System.out.println("Dato invalido: " + ex.getMessage());
            }
        }
    }

    private <T> T promptValidator(String prompt, CheckedFunction<String, T> mapper) {
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
