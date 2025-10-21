package app.adapter.in.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.builder.MedicalOrderBuilder;
import app.adapter.in.builder.MedicalRecordBuilder;
import app.adapter.in.builder.OrderItemBuilder;
import app.adapter.in.rest.request.MedicalOrderRequest;
import app.adapter.in.rest.request.MedicalRecordRequest;
import app.adapter.in.rest.request.OrderItemRequest;
import app.adapter.in.validators.PatientValidator;
import app.application.exceptions.BusinessException;
import app.application.exceptions.InputsException;
import app.application.usecase.DoctorUseCase;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.OrderItem;
import app.domain.model.Patient;

/**
 * Controlador REST para las funcionalidades de los médicos. Permite
 * registrar órdenes médicas y registros clínicos, así como consultar
 * órdenes existentes para un paciente.
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private OrderItemBuilder orderItemBuilder;
    @Autowired
    private MedicalOrderBuilder medicalOrderBuilder;
    @Autowired
    private MedicalRecordBuilder medicalRecordBuilder;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private DoctorUseCase doctorUseCase;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody MedicalOrderRequest request) {
        try {
            List<OrderItem> items = new ArrayList<>();
            if (request.getItems() != null) {
                for (OrderItemRequest itemReq : request.getItems()) {
                    OrderItem item = orderItemBuilder.build(
                            itemReq.getItemNumber(),
                            itemReq.getType(),
                            itemReq.getName(),
                            itemReq.getDose(),
                            itemReq.getTreatmentDuration(),
                            itemReq.getQuantity(),
                            itemReq.getFrequency(),
                            itemReq.getCost(),
                            itemReq.getRequiresSpecialist(),
                            itemReq.getSpecialistTypeId()
                    );
                    items.add(item);
                }
            }
            MedicalOrder order = medicalOrderBuilder.build(
                    request.getDoctorDocument(),
                    request.getPatientId(),
                    items
            );
            doctorUseCase.createMedicalOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/records")
    public ResponseEntity<?> createRecord(@RequestBody MedicalRecordRequest request) {
        try {
            MedicalRecord record = medicalRecordBuilder.build(
                    request.getDoctorDocument(),
                    request.getPatientId(),
                    request.getOrderId(),
                    request.getMotive(),
                    request.getSymptoms(),
                    request.getDiagnosis()
            );
            doctorUseCase.createMedicalRecord(record);
            return ResponseEntity.status(HttpStatus.CREATED).body(record);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/orders/{patientId}")
    public ResponseEntity<?> searchOrders(@PathVariable String patientId) {
        try {
            Patient patient = new Patient();
            patient.setDocument(patientValidator.documentValidator(patientId));
            List<MedicalOrder> orders = doctorUseCase.searchMedicalOrders(patient);
            return ResponseEntity.ok(orders);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}