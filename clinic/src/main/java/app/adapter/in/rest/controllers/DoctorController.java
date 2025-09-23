package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import app.application.usecase.DoctorUseCase;
import app.adapter.in.builder.ClinicalOrderBuilder;
import app.adapter.in.builder.ClinicalHistoryBuilder;

import app.adapter.in.rest.request.ClinicalOrderRequest;
import app.adapter.in.rest.request.ClinicalHistoryRequest;

import app.domain.model.ClinicalOrder;
import app.domain.model.ClinicalHistory;
import app.application.exceptions.InputsException;
import app.application.exceptions.BusinessException;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired private DoctorUseCase doctorUseCase;
    @Autowired private ClinicalOrderBuilder clinicalOrderBuilder;
    @Autowired private ClinicalHistoryBuilder clinicalHistoryBuilder;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody ClinicalOrderRequest req) {
        try {
            ClinicalOrder order = clinicalOrderBuilder.build(
                req.getNumberOrder(), req.getPatientDocument(), req.getProfessionalDocument(), req.getCreationDate()
            );
            doctorUseCase.createOrder(order);
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
    public ResponseEntity<?> recordClinicalHistory(@RequestBody ClinicalHistoryRequest req) {
        try {
            ClinicalHistory h = clinicalHistoryBuilder.build(
                req.getPatientDocument(), req.getDate(), req.getProfessionalDocument(),
                req.getMotive(), req.getSymptoms(), req.getDiagnosis()
            );
            doctorUseCase.recordClinicalHistory(h);
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
