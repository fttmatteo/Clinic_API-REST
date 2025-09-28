package app.adapter.in.client;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.InvoiceBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.adapter.in.validators.PatientValidator;
import app.application.usecase.AdministrativeUseCase;
import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;

/**
 * Cliente de consola para el personal administrativo.
 * Permite registrar pacientes, emitir facturas y consultar las órdenes
 * médicas asociadas a un paciente.
 */
@Controller
public class AdministrativeClient {

    private static final String MENU =
        "Ingrese una opción:\n" +
        "1. Registrar paciente\n" +
        "2. Crear factura\n" +
        "3. Consultar órdenes de un paciente\n" +
        "4. Salir";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private AdministrativeUseCase administrativeUseCase;
    @Autowired
    private PatientBuilder patientBuilder;
    @Autowired
    private InvoiceBuilder invoiceBuilder;
    @Autowired
    private PatientValidator patientValidator;

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
                System.out.println("Hasta luego. Cerrando sesión.");
                return false;
            }
            default: {
                System.out.println("Opción inválida. Por favor intente de nuevo.");
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private Patient readPatientData() throws Exception {
        System.out.println("Ingrese el nombre completo del paciente:");
        String fullName = reader.nextLine();
        System.out.println("Ingrese el número de documento:");
        String document = reader.nextLine();
        System.out.println("Ingrese la fecha de nacimiento (DD/MM/YYYY):");
        String birthDate = reader.nextLine();
        System.out.println("Ingrese el género (MASCULINO, FEMENINO, OTRO):");
        String gender = reader.nextLine();
        System.out.println("Ingrese la dirección:");
        String address = reader.nextLine();
        System.out.println("Ingrese el teléfono (10 dígitos):");
        String phone = reader.nextLine();
        System.out.println("Ingrese el correo electrónico:");
        String email = reader.nextLine();
        System.out.println("Ingrese el nombre del contacto de emergencia:");
        String contactFirstName = reader.nextLine();
        System.out.println("Ingrese el apellido del contacto de emergencia:");
        String contactLastName = reader.nextLine();
        System.out.println("Ingrese la relación del contacto con el paciente:");
        String contactRelation = reader.nextLine();
        System.out.println("Ingrese el teléfono del contacto de emergencia:");
        String contactPhone = reader.nextLine();
        System.out.println("Ingrese el nombre de la compañía de seguros:");
        String companyName = reader.nextLine();
        System.out.println("Ingrese el número de póliza:");
        String policyNumber = reader.nextLine();
        System.out.println("Ingrese el estado de la póliza (si/no o true/false):");
        String policyStatus = reader.nextLine();
        System.out.println("Ingrese la fecha de vencimiento de la póliza (DD/MM/YYYY):");
        String policyExpiry = reader.nextLine();

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
        System.out.println("Ingrese el ID del paciente:");
        String patientId = reader.nextLine();
        System.out.println("Ingrese el documento del médico (deje en blanco si no aplica):");
        String doctorDoc = reader.nextLine();
        System.out.println("Ingrese el nombre del producto o servicio:");
        String productName = reader.nextLine();
        System.out.println("Ingrese el monto:");
        String productAmount = reader.nextLine();
        System.out.println("¿Es un medicamento? (si/no):");
        String isMedicine = reader.nextLine();
        System.out.println("Ingrese el identificador de la orden (dejar en blanco si no aplica):");
        String orderId = reader.nextLine();
        return invoiceBuilder.build(patientId, doctorDoc, productName, productAmount, isMedicine, orderId);
    }

    private void readAndPrintOrders() throws Exception {
        System.out.println("Ingrese el documento del paciente para consultar sus órdenes:");
        String document = reader.nextLine();
        long doc = patientValidator.documentValidator(document);
        Patient patient = new Patient();
        patient.setDocument(doc);
        List<MedicalOrder> orders = administrativeUseCase.searchMedicalOrders(patient);
        if (orders == null || orders.isEmpty()) {
            System.out.println("No se encontraron órdenes para el paciente especificado.");
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
                System.out.println("Médico: " + order.getDoctor().getDocument());
            }
            if (order.getPatient() != null) {
                System.out.println("Paciente: " + order.getPatient().getDocument());
            }
            if (order.getItems() != null && !order.getItems().isEmpty()) {
                System.out.println("Ítems:");
                order.getItems().forEach(item -> {
                    System.out.println("  Ítem #" + item.getItemNumber() +
                                       " | Tipo: " + item.getType() +
                                       " | Nombre: " + item.getName() +
                                       " | Costo: " + item.getCost());
                });
            }
            System.out.println("-----------------------------------");
        }
    }
}
