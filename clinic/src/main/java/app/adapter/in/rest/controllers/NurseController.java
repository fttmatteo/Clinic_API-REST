package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class NurseController {

    @Autowired
    private VitalSignsBuilder vitalSignsBuilder;
    @Autowired
    private NurseUseCase nurseUseCase;

    @PostMapping("/vital-signs")
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
}