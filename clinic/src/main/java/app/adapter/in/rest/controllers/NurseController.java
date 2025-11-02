package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.builder.VitalSignsBuilder;
import app.adapter.in.rest.request.VitalSignsRequest;
import app.application.exceptions.BusinessException;
import app.application.exceptions.InputsException;
import app.application.usecase.NurseUseCase;
import app.domain.model.VitalSignsRecord;

/**
 * Controlador REST para las operaciones de las enfermeras. Permite
 * registrar los signos vitales de los pacientes. 
 */
@RestController
@RequestMapping("/nurse")
@PreAuthorize("hasRole('NURSE')")
public class NurseController {

    @Autowired
    private VitalSignsBuilder vitalSignsBuilder;
    @Autowired
    private NurseUseCase nurseUseCase;

    @Autowired
    private app.adapter.in.builder.OrderExecutionBuilder orderExecutionBuilder;

    @PostMapping("/vital-signs")
        @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<?> recordVitalSigns(@RequestBody VitalSignsRequest request) {
        try {
            VitalSignsRecord record = vitalSignsBuilder.build(
                    request.getNurseDocument(),
                    request.getPatientId(),
                    request.getBloodPressure(),
                    request.getTemperature(),
                    request.getPulse(),
                    request.getOxygenLevel()
            );
            nurseUseCase.recordVitalSigns(record);
            return ResponseEntity.status(HttpStatus.CREATED).body(record);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint para registrar la administración de un medicamento o la
     * realización de un procedimiento indicado en una orden médica. La
     * enfermera debe proporcionar su cédula, la cantidad aplicada y las
     * observaciones opcionales. La ruta identifica la orden y el ítem
     * correspondiente.
     */
    @PostMapping("/orders/{orderId}/items/{itemNumber}/execute")
        @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<?> executeOrderItem(
            @org.springframework.web.bind.annotation.PathVariable String orderId,
            @org.springframework.web.bind.annotation.PathVariable String itemNumber,
            @RequestBody app.adapter.in.rest.request.OrderExecutionRequest request) {
        try {
            // Construir el registro de ejecución
            var record = orderExecutionBuilder.build(
                    request.getNurseDocument(),
                    request.getAmount(),
                    request.getNotes()
            );
            // Convertir identificadores
            Long oId = new app.adapter.in.validators.SimpleValidator() {
            }.longValidator("identificador de la orden", orderId);
            Integer iNum = new app.adapter.in.validators.SimpleValidator() {
            }.integerValidator("número de ítem", itemNumber);
            nurseUseCase.executeOrderItem(record, oId, iNum);
            return ResponseEntity.status(HttpStatus.CREATED).body(record);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}