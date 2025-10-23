package app.adapter.in.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.builder.MedicineBuilder;
import app.adapter.in.builder.ProcedureBuilder;
import app.adapter.in.builder.DiagnosticAidBuilder;
import app.adapter.in.rest.request.MedicineRequest;
import app.adapter.in.rest.request.ProcedureRequest;
import app.adapter.in.rest.request.DiagnosticAidRequest;
import app.application.exceptions.BusinessException;
import app.application.exceptions.InputsException;
import app.application.usecase.InformationSupportUseCase;
import app.domain.model.DiagnosticAid;
import app.domain.model.Medicine;
import app.domain.model.Procedure;

/**
 * Controlador REST para el personal de soporte de información.  Permite
 * administrar el inventario de medicamentos, procedimientos y ayudas
 * diagnósticas.
 */
@RestController
@RequestMapping("/support")
@PreAuthorize("hasRole('INFORMATION_SUPPORT')")
public class InformationSupportController {

    @Autowired
    private MedicineBuilder medicineBuilder;
    @Autowired
    private ProcedureBuilder procedureBuilder;
    @Autowired
    private DiagnosticAidBuilder diagnosticAidBuilder;
    @Autowired
    private InformationSupportUseCase supportUseCase;

    @PostMapping("/medicines")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> createMedicine(@RequestBody MedicineRequest request) {
        try {
            Medicine medicine = medicineBuilder.build(request.getId(), request.getName(), request.getCost());
            supportUseCase.createMedicine(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicine);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/medicines")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> getMedicines() {
        try {
            List<Medicine> list = supportUseCase.getMedicines();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/procedures")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> createProcedure(@RequestBody ProcedureRequest request) {
        try {
            Procedure procedure = procedureBuilder.build(request.getId(), request.getName(), request.getCost());
            supportUseCase.createProcedure(procedure);
            return ResponseEntity.status(HttpStatus.CREATED).body(procedure);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/procedures")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> getProcedures() {
        try {
            List<Procedure> list = supportUseCase.getProcedures();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/diagnostic-aids")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> createDiagnosticAid(@RequestBody DiagnosticAidRequest request) {
        try {
            DiagnosticAid aid = diagnosticAidBuilder.build(request.getId(), request.getName(), request.getCost());
            supportUseCase.createDiagnosticAid(aid);
            return ResponseEntity.status(HttpStatus.CREATED).body(aid);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/diagnostic-aids")
        @PreAuthorize("hasRole('INFORMATION_SUPPORT')")
    public ResponseEntity<?> getDiagnosticAids() {
        try {
            List<DiagnosticAid> list = supportUseCase.getDiagnosticAids();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}