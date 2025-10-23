package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.builder.InvoiceBuilder;
import app.adapter.in.builder.PatientBuilder;
import app.adapter.in.rest.request.InvoiceRequest;
import app.adapter.in.rest.request.PatientRequest;
import app.adapter.in.validators.PatientValidator;
import app.application.exceptions.BusinessException;
import app.application.exceptions.InputsException;
import app.application.usecase.AdministrativeUseCase;
import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;

/**
 * Controlador REST para las operaciones del personal administrativo. Permite
 * registrar pacientes, emitir facturas y consultar las órdenes médicas
 * asociadas a un paciente.
 */
@RestController
@RequestMapping("/administrative")
@PreAuthorize("hasRole('PERSONAL_ADMINISTRATIVE')")
public class AdministrativeController {

    @Autowired
    private PatientBuilder patientBuilder;
    @Autowired
    private InvoiceBuilder invoiceBuilder;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private AdministrativeUseCase administrativeUseCase;

    @PostMapping("/patients")
        @PreAuthorize("hasRole('PERSONAL_ADMINISTRATIVE')")
    public ResponseEntity<?> createPatient(@RequestBody PatientRequest request) {
        try {
            Patient patient = patientBuilder.build(
                    request.getFullName(),
                    request.getDocument(),
                    request.getBirthDate(),
                    request.getGender(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getContactFirstName(),
                    request.getContactLastName(),
                    request.getContactRelation(),
                    request.getContactPhone(),
                    request.getCompanyName(),
                    request.getPolicyNumber(),
                    request.getPolicyStatus(),
                    request.getPolicyExpiry()
            );
            administrativeUseCase.createPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(patient);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/invoices")
        @PreAuthorize("hasRole('PERSONAL_ADMINISTRATIVE')")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest request) {
        try {
            Invoice invoice = invoiceBuilder.build(
                    request.getPatientId(),
                    request.getDoctorDocument(),
                    request.getProductName(),
                    request.getProductAmount(),
                    request.getIsMedicine(),
                    request.getOrderId()
            );
            administrativeUseCase.createInvoice(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/orders/{patientId}")
        @PreAuthorize("hasRole('PERSONAL_ADMINISTRATIVE')")
    public ResponseEntity<?> searchOrders(@PathVariable String patientId) {
        try {
            Patient patient = new Patient();
            patient.setDocument(patientValidator.documentValidator(patientId));
            List<MedicalOrder> orders = administrativeUseCase.searchMedicalOrders(patient);
            return ResponseEntity.ok(orders);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}