package app.adapter.in.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.adapter.in.builder.MedicalOrderBuilder;
import app.adapter.in.builder.MedicalRecordBuilder;
import app.adapter.in.builder.OrderItemBuilder;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.OrderItem;
import app.domain.model.Patient;
import app.application.usecase.DoctorUseCase;

/**
 * Cliente de consola para médicos.
 * Permite crear órdenes médicas e historias clínicas y consultar las
 * órdenes asociadas a un paciente.
 */
@Controller
public class DoctorClient {

    private static final String MENU =
        "Ingrese una opción:\n" +
        "1. Crear orden médica\n" +
        "2. Registrar historia clínica\n" +
        "3. Consultar órdenes de un paciente\n" +
        "4. Salir";

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
    private app.domain.services.InventoryService inventoryService;

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

    private MedicalOrder readOrderData() throws Exception {
        System.out.println("Ingrese el identificador de la orden médica (máximo 6 dígitos):");
        String id = reader.nextLine();
        System.out.println("Ingrese el documento del médico que genera la orden:");
        String doctorDocument = reader.nextLine();
        System.out.println("Ingrese el documento del paciente:");
        String patientDocument = reader.nextLine();

        System.out.println("¿Desea usar la lista de ítems predeterminada? (si/no):");
        String useDefault = reader.nextLine();
        List<OrderItem> items;
        if (useDefault.equalsIgnoreCase("si") || useDefault.equalsIgnoreCase("s") || useDefault.equalsIgnoreCase("true")) {
            items = createDefaultOrderItems();
        } else {
            System.out.println("Ingrese la cantidad de ítems que tendrá la orden:");
            String countStr = reader.nextLine();
            int count;
            try {
                count = Integer.parseInt(countStr);
            } catch (NumberFormatException ex) {
                throw new Exception("La cantidad de ítems debe ser un número entero");
            }
            if (count < 1) {
                throw new Exception("La orden debe contener al menos un ítem");
            }
            items = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                System.out.println("Ingrese los datos para el ítem #" + i);
                OrderItem item = readOrderItemData(i);
                items.add(item);
            }
        }
        return orderBuilder.build(id, doctorDocument, patientDocument, items);
    }

    private OrderItem readOrderItemData(int index) throws Exception {
        System.out.println("Número de ítem:");
        String itemNumber = reader.nextLine();
        System.out.println("Tipo de ítem (MEDICINE, PROCEDURE, DIAGNOSTIC_AID):");
        String type = reader.nextLine();
        String typeUpper = type.trim().toUpperCase();
        String name;
        if (typeUpper.equals("MEDICINE")) {
            System.out.println("Seleccione un medicamento de la lista o ingrese un nombre manualmente:");
            var meds = inventoryService.getMedicines();
            for (int i = 0; i < meds.size(); i++) {
                System.out.println((i + 1) + ". " + meds.get(i).getId() + " - " + meds.get(i).getName());
            }
            String inputName = reader.nextLine();
            try {
                int idx = Integer.parseInt(inputName);
                if (idx >= 1 && idx <= meds.size()) {
                    name = meds.get(idx - 1).getName();
                } else {
                    name = inputName;
                }
            } catch (NumberFormatException nfe) {
                var medicine = inventoryService.findMedicineById(inputName);
                name = (medicine != null) ? medicine.getName() : inputName;
            }
        } else if (typeUpper.equals("PROCEDURE")) {
            System.out.println("Seleccione un procedimiento de la lista o ingrese un nombre manualmente:");
            var procs = inventoryService.getProcedures();
            for (int i = 0; i < procs.size(); i++) {
                System.out.println((i + 1) + ". " + procs.get(i).getId() + " - " + procs.get(i).getName());
            }
            String inputName = reader.nextLine();
            try {
                int idx = Integer.parseInt(inputName);
                if (idx >= 1 && idx <= procs.size()) {
                    name = procs.get(idx - 1).getName();
                } else {
                    name = inputName;
                }
            } catch (NumberFormatException nfe) {
                var procedure = inventoryService.findProcedureById(inputName);
                name = (procedure != null) ? procedure.getName() : inputName;
            }
        } else if (typeUpper.equals("DIAGNOSTIC_AID")) {
            System.out.println("Seleccione una ayuda diagnóstica de la lista o ingrese un nombre manualmente:");
            var diags = inventoryService.getDiagnosticAids();
            for (int i = 0; i < diags.size(); i++) {
                System.out.println((i + 1) + ". " + diags.get(i).getId() + " - " + diags.get(i).getName());
            }
            String inputName = reader.nextLine();
            try {
                int idx = Integer.parseInt(inputName);
                if (idx >= 1 && idx <= diags.size()) {
                    name = diags.get(idx - 1).getName();
                } else {
                    name = inputName;
                }
            } catch (NumberFormatException nfe) {
                var diag = inventoryService.findDiagnosticAidById(inputName);
                name = (diag != null) ? diag.getName() : inputName;
            }
        } else {
            System.out.println("Nombre del ítem:");
            name = reader.nextLine();
        }
        String dose = null;
        String treatmentDuration = null;
        String quantity = null;
        String frequency = null;
        String requiresSpecialist = null;
        String specialistTypeId = null;
        if (typeUpper.equals("MEDICINE")) {
            System.out.println("Dosis del medicamento:");
            dose = reader.nextLine();
            System.out.println("Duración del tratamiento:");
            treatmentDuration = reader.nextLine();
        } else if (typeUpper.equals("PROCEDURE")) {
            System.out.println("Cantidad del procedimiento:");
            quantity = reader.nextLine();
            System.out.println("Frecuencia del procedimiento:");
            frequency = reader.nextLine();
        } else if (typeUpper.equals("DIAGNOSTIC_AID")) {
            System.out.println("Cantidad de ayudas diagnósticas:");
            quantity = reader.nextLine();
        }
        System.out.println("Costo:");
        String cost = reader.nextLine();
        if (typeUpper.equals("PROCEDURE") || typeUpper.equals("DIAGNOSTIC_AID")) {
            System.out.println("¿Requiere especialista? (si/no):");
            requiresSpecialist = reader.nextLine();
            if (requiresSpecialist.equalsIgnoreCase("si") || requiresSpecialist.equalsIgnoreCase("true")) {
                System.out.println("Ingrese el identificador del tipo de especialidad:");
                specialistTypeId = reader.nextLine();
            }
        }
        return orderItemBuilder.build(
            itemNumber,
            type,
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
        System.out.println("Ingrese el documento del médico que registra la historia:");
        String doctorDocument = reader.nextLine();
        System.out.println("Ingrese el documento del paciente:");
        String patientDocument = reader.nextLine();
        System.out.println("Ingrese el identificador de la orden asociada (dejar en blanco si no aplica):");
        String orderId = reader.nextLine();
        System.out.println("Ingrese el motivo de la consulta:");
        String motive = reader.nextLine();
        System.out.println("Ingrese los síntomas reportados:");
        String symptoms = reader.nextLine();
        System.out.println("Ingrese el diagnóstico:");
        String diagnosis = reader.nextLine();
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
        System.out.println("Ingrese el documento del paciente para consultar sus órdenes:");
        String document = reader.nextLine();
        long doc = patientValidator.documentValidator(document);
        Patient patient = new Patient();
        patient.setDocument(doc);
        List<MedicalOrder> orders = doctorUseCase.searchMedicalOrders(patient);
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

    private List<OrderItem> createDefaultOrderItems() throws Exception {
        List<OrderItem> defaultItems = new ArrayList<>();
        if (!inventoryService.getMedicines().isEmpty()) {
            var med = inventoryService.getMedicines().get(0);
            defaultItems.add(orderItemBuilder.build(
                "1",
                "MEDICINE",
                med.getName(),
                "500 mg",
                "5 días",
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
                "único",
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
}