package app.adapter.in.rest.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import app.application.usecase.EmployeeUseCase;
import app.adapter.in.builder.EmployeeBuilder;
import app.domain.model.Employee;
import app.domain.services.DeleteEmployee;
import app.application.exceptions.InputsException;
import app.application.exceptions.BusinessException;

@RestController
@RequestMapping("/api/admin")
public class EmployeeController {

    @Autowired private EmployeeUseCase employeeUseCase;
    @Autowired private EmployeeBuilder employeeBuilder;
    @Autowired private DeleteEmployee deleteEmployee;

    @PostMapping("/employees/doctor")
    public ResponseEntity<?> createDoctor(
            @RequestParam String fullName,
            @RequestParam String document,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String birthDate,   // yyyy-MM-dd
            @RequestParam String address,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            Employee employee = employeeBuilder.build(fullName, document, email, phoneNumber, birthDate, address, username, password);
            employeeUseCase.createDoctor(employee) ;
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employees/nurse")
    public ResponseEntity<?> createNurse(
            @RequestParam String fullName,
            @RequestParam String document,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String birthDate,
            @RequestParam String address,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            Employee employee = employeeBuilder.build(fullName, document, email, phoneNumber, birthDate, address, username, password);
            employeeUseCase.createNurse(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employees/administrative")
    public ResponseEntity<?> createAdministrative(
            @RequestParam String fullName,
            @RequestParam String document,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String birthDate,
            @RequestParam String address,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            Employee employee = employeeBuilder.build(fullName, document, email, phoneNumber, birthDate, address, username, password);
            employeeUseCase.createAdministrative(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employees/human-resources")
    public ResponseEntity<?> createHumanResources(
            @RequestParam String fullName,
            @RequestParam String document,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String birthDate,
            @RequestParam String address,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            Employee employee = employeeBuilder.build(fullName, document, email, phoneNumber, birthDate, address, username, password);
            employeeUseCase.createHR(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employees/information-support")
    public ResponseEntity<?> createInformationSupport(
            @RequestParam String fullName,
            @RequestParam String document,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String birthDate,
            @RequestParam String address,
            @RequestParam String username,
            @RequestParam String password
    ) {
        try {
            Employee employee = employeeBuilder.build(fullName, document, email, phoneNumber, birthDate, address, username, password);
            employeeUseCase.createIS(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InputsException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (BusinessException be) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/employees/{document}")
    public ResponseEntity<?> deleteEmployeeByDocument(@PathVariable int document) {
        try {
            deleteEmployee.deleteByDocument(document);
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
