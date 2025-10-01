package app.adapter.in.client;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.InvoiceBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.adapter.in.validators.EmergencyContactValidator;
import app.adapter.in.validators.InsurancePolicyValidator;
import app.adapter.in.validators.InvoiceValidator;
import app.adapter.in.validators.PatientValidator;
import app.application.exceptions.InputsException;
import app.application.usecase.AdministrativeUseCase;
import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;

/**
 * Cliente de consola para el personal administrativo.
 * Permite registrar pacientes, emitir facturas y consultar las ordenes
 * medicas asociadas a un paciente.
 */
@Controller
public class AdministrativeClient {

    private static final String MENU =
        "---------- AREA ADMINISTRATIVA ----------\n" +
        "Ingrese una opcion:\n" +
        "1. Registrar paciente\n" +
        "2. Crear factura\n" +
        "3. Consultar ordenes de un paciente\n" +
        "4. SALIR\n";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private AdministrativeUseCase administrativeUseCase;
    @Autowired
    private PatientBuilder patientBuilder;
    @Autowired
    private InvoiceBuilder invoiceBuilder;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private EmergencyContactValidator emergencyContactValidator;
    @Autowired
    private InsurancePolicyValidator insurancePolicyValidator;
    @Autowired
    private InvoiceValidator invoiceValidator;

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
                Patient patient = readPatientData();
                administrativeUseCase.createPatient(patient);
                return true;
            }
            case "2": {
                Invoice invoice = readInvoiceData();
                administrativeUseCase.createInvoice(invoice);
                return true;
            }
            case "3": {
                readAndPrintOrders();
                return true;
            }
            case "4": {
                System.out.println("Cerrando sesion del area administrativa...\n");
                return false;
            }
            default: {
                System.out.println("Opcion invalida. Por favor elija una opcion del 1 al 4.\n");
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private Patient readPatientData() throws Exception {
        String fullName = askUntilValid("Ingrese el nombre completo del paciente:", patientValidator::fullNameValidator);
        String document = askUntilValid("Ingrese el numero de documento:", input -> {
            patientValidator.documentValidator(input);
            return input;
        });
        String birthDate = askUntilValid("Ingrese la fecha de nacimiento (DD/MM/YYYY):", input -> {
            patientValidator.birthDateValidator(input);
            return input;
        });
        String gender = askUntilValid("Ingrese el genero (MASCULINO, FEMENINO, OTRO):", input -> {
            patientValidator.genderValidator(input);
            return input;
        });
        String address = askUntilValid("Ingrese la direccion:", input -> {
            patientValidator.addressValidator(input);
            return input;
        });
        String phone = askUntilValid("Ingrese el telefono (10 digitos):", input -> {
            patientValidator.phoneValidator(input);
            return input;
        });
        String email = askUntilValid("Ingrese el correo electronico:", input -> {
            patientValidator.emailValidator(input);
            return input;
        });
        String contactFirstName = askUntilValid("Ingrese el nombre del contacto de emergencia:", input -> {
            emergencyContactValidator.firstNameValidator(input);
            return input;
        });
        String contactLastName = askUntilValid("Ingrese el apellido del contacto de emergencia:", input -> {
            emergencyContactValidator.lastNameValidator(input);
            return input;
        });
        String contactRelation = askUntilValid("Ingrese la relacion del contacto con el paciente:", input -> {
            emergencyContactValidator.relationShipValidator(input);
            return input;
        });
        String contactPhone = askUntilValid("Ingrese el telefono del contacto de emergencia:", input -> {
            emergencyContactValidator.phoneValidator(input);
            return input;
        });
        String companyName = askUntilValid("Ingrese el nombre de la compania de seguros:", input -> {
            insurancePolicyValidator.companyNameValidator(input);
            return input;
        });
        String policyNumber = askUntilValid("Ingrese el numero de poliza:", input -> {
            insurancePolicyValidator.policyNumberValidator(input);
            return input;
        });
        String policyStatus = askUntilValid("Ingrese el estado de la poliza (si/no o true/false):", input -> {
            insurancePolicyValidator.activeValidator(input);
            return input;
        });
        String policyExpiry = askUntilValid("Ingrese la fecha de vencimiento de la poliza (DD/MM/YYYY):", input -> {
            insurancePolicyValidator.expiryDateValidator(input);
            return input;
        });

        return patientBuilder.build(
            fullName,
            document,
            birthDate,
            gender,
            address,
            phone,
            email,
            contactFirstName,
            contactLastName,
            contactRelation,
            contactPhone,
            companyName,
            policyNumber,
            policyStatus,
            policyExpiry
        );
    }

    private Invoice readInvoiceData() throws Exception {
        String patientId = askUntilValid("Ingrese el ID del paciente:", input -> {
            invoiceValidator.patientIdValidator(input);
            return input;
        });
        String doctorDoc = askOptional("Ingrese el documento del medico (deje en blanco si no aplica):", invoiceValidator::doctorDocumentValidator);
        String productName = askUntilValid("Ingrese el nombre del producto o servicio:", invoiceValidator::productNameValidator);
        String productAmount = askUntilValid("Ingrese el monto:", input -> {
            invoiceValidator.amountValidator(input);
            return input;
        });
        boolean isMedicine = askUntilValid("Es un medicamento? (si/no):", invoiceValidator::isMedicineValidator);
        String orderId = "";
        if (isMedicine) {
            orderId = askUntilValid("Ingrese el identificador de la orden:", input -> {
                invoiceValidator.orderIdValidator(input);
                return input;
            });
        }
        return invoiceBuilder.build(patientId, doctorDoc, productName, productAmount, Boolean.toString(isMedicine), orderId);
    }

    private void readAndPrintOrders() throws Exception {
        long document = askUntilValid("Ingrese el documento del paciente para consultar sus ordenes:", patientValidator::documentValidator);
        Patient patient = new Patient();
        patient.setDocument(document);
        List<MedicalOrder> orders = administrativeUseCase.searchMedicalOrders(patient);
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

    private <T> T askUntilValid(String prompt, CheckedFunction<String, T> mapper) {
        while (true) {
            System.out.println(prompt);
            String input = reader.nextLine();
            String value = input == null ? "" : input.trim();
            try {
                return mapper.apply(value);
            } catch (InputsException ex) {
                System.out.println("Dato invalido: " + ex.getMessage());
            }
        }
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
            } catch (InputsException ex) {
                System.out.println("Dato invalido: " + ex.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface CheckedFunction<I, O> {
        O apply(I value) throws InputsException;
    }
}
