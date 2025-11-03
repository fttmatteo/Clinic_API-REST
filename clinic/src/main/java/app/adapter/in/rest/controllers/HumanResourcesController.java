package app.adapter.in.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.builder.EmployeeBuilder;
import app.adapter.in.rest.request.EmployeeRequest;
import app.application.exceptions.BusinessException;
import app.application.exceptions.InputsException;
import app.application.usecase.HumanResourcesUseCase;
import app.domain.model.Employee;

/**
 * Controlador REST para las operaciones de Recursos Humanos. Permite crear
 * empleados en diferentes roles (doctor, enfermera, administrativo y
 * soporte de información).
 */
@RestController
@RequestMapping("/employees")
@PreAuthorize("hasRole('HUMAN_RESOURCES')")
public class HumanResourcesController {

    @Autowired
    private EmployeeBuilder employeeBuilder;
    @Autowired
    private HumanResourcesUseCase humanResourcesUseCase;

    @Autowired
    private app.adapter.in.validators.EmployeeValidator employeeValidator;

    @PostMapping("/doctor")
        @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    public ResponseEntity<?> createDoctor(@RequestBody EmployeeRequest request) {
        try {
            Employee employee = employeeBuilder.build(
                    request.getFullName(),
                    request.getDocument(),
                    request.getBirthDate(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword()
            );
            humanResourcesUseCase.createDoctor(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/nurse")
        @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    public ResponseEntity<?> createNurse(@RequestBody EmployeeRequest request) {
        try {
            Employee employee = employeeBuilder.build(
                    request.getFullName(),
                    request.getDocument(),
                    request.getBirthDate(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword()
            );
            humanResourcesUseCase.createNurse(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/administrative")
        @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    public ResponseEntity<?> createAdministrative(@RequestBody EmployeeRequest request) {
        try {
            Employee employee = employeeBuilder.build(
                    request.getFullName(),
                    request.getDocument(),
                    request.getBirthDate(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword()
            );
            humanResourcesUseCase.createAdministrative(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/information-support")
        @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    public ResponseEntity<?> createInformationSupport(@RequestBody EmployeeRequest request) {
        try {
            Employee employee = employeeBuilder.build(
                    request.getFullName(),
                    request.getDocument(),
                    request.getBirthDate(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword()
            );
            humanResourcesUseCase.createInformationSupport(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{document}")
        @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    public ResponseEntity<?> deleteEmployee(@PathVariable String document) {
        try {
            long doc = employeeValidator.documentValidator(document);
            humanResourcesUseCase.deleteEmployee(doc);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}