package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import app.application.usecase.DoctorUseCase;
import app.adapter.in.builder.ClinicalOrderBuilder;
import app.adapter.in.builder.OrderMedicationItemBuilder;
import app.adapter.in.builder.OrderProcedureItemBuilder;
import app.adapter.in.builder.OrderDiagnosticAidItemBuilder;
import app.adapter.in.builder.ClinicalHistoryBuilder;

import app.domain.model.ClinicalOrder;
import app.domain.model.OrderMedicationItem;
import app.domain.model.OrderProcedureItem;
import app.domain.model.OrderDiagnosticAidItem;
import app.domain.model.ClinicalHistory;
import app.application.exceptions.InputsException;
import app.application.exceptions.BusinessException;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired private DoctorUseCase doctorUseCase;
    @Autowired private ClinicalOrderBuilder clinicalOrderBuilder;
    @Autowired private OrderMedicationItemBuilder orderMedicationItemBuilder;
    @Autowired private OrderProcedureItemBuilder orderProcedureItemBuilder;
    @Autowired private OrderDiagnosticAidItemBuilder orderDiagnosticAidItemBuilder;
    @Autowired private ClinicalHistoryBuilder clinicalHistoryBuilder;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(
            @RequestParam String numberOrder,
            @RequestParam String patientDocument,
            @RequestParam String doctorDocuemnt,
            @RequestParam String creationDate   
    ) {
        try {
            ClinicalOrder clinicalOrder = clinicalOrderBuilder.build(numberOrder, patientDocument, doctorDocuemnt, creationDate);
            doctorUseCase.createOrder(clinicalOrder);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/orders/medications")
    public ResponseEntity<?> addMedicationItem(
            @RequestParam String numberOrder,
            @RequestParam String item,
            @RequestParam String idMedication,
            @RequestParam String dose,
            @RequestParam String treatmentDuration,
            @RequestParam String price
    ) {
        try {
            OrderMedicationItem orderMedicationItem = orderMedicationItemBuilder.build(numberOrder, item, idMedication, dose, treatmentDuration, price);
            doctorUseCase.addMedicationItem(orderMedicationItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/orders/procedures")
    public ResponseEntity<?> addProcedureItem(
            @RequestParam String numberOrder,
            @RequestParam String item,
            @RequestParam String idProcedure,
            @RequestParam String quantity,
            @RequestParam String frequency,
            @RequestParam String specialistRequired,  
            @RequestParam String specialistTypeId,  
            @RequestParam String price
    ) {
        try {
            OrderProcedureItem orderProcedureItem = orderProcedureItemBuilder.build(
                numberOrder, item, idProcedure, quantity, frequency, specialistRequired, specialistTypeId, price
            );
            doctorUseCase.addProcedureItem(orderProcedureItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/orders/diagnostics")
    public ResponseEntity<?> addDiagnosticAidItem(
            @RequestParam String numberOrder,
            @RequestParam String item,
            @RequestParam String idDiagnosticAid,
            @RequestParam String quantity,
            @RequestParam String specialistRequired,
            @RequestParam String specialistTypeId,
            @RequestParam String price
    ) {
        try {
            OrderDiagnosticAidItem orderDiagnosticAidItem = orderDiagnosticAidItemBuilder.build(
                numberOrder, item, idDiagnosticAid, quantity, specialistRequired, specialistTypeId, price
            );
            doctorUseCase.addDiagnosticAidItem(orderDiagnosticAidItem);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/clinical-history")
    public ResponseEntity<?> recordClinicalHistory(
            @RequestParam String patientDocument,
            @RequestParam String date,               
            @RequestParam String doctorDocument,
            @RequestParam String motive,
            @RequestParam String symptoms,
            @RequestParam String diagnosis
    ) {
        try {
            ClinicalHistory clinicalHistory = clinicalHistoryBuilder.build(
                patientDocument, date, doctorDocument, motive, symptoms, diagnosis
            );
            doctorUseCase.recordClinicalHistory(clinicalHistory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/orders/patient/{patientDocument}")
    public ResponseEntity<?> findOrdersByPatient(@PathVariable int patientDocument) {
        try {
            List<ClinicalOrder> list = doctorUseCase.findOrdersByPatient(patientDocument);
            return ResponseEntity.ok(list);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
