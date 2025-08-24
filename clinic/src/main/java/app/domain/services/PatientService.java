package app.domain.services;

import java.util.List;
import app.domain.common.exceptions.ConflictException;
import app.domain.common.exceptions.NotFoundException;
import app.domain.common.exceptions.ValidationException;
import app.domain.model.Patient;
import app.domain.ports.PatientPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import static app.domain.services.validation.Validators.*;

public class PatientService {

    private final PatientPort patientPort;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PatientService(PatientPort patientPort) {
        this.patientPort = Objects.requireNonNull(patientPort);
    }

    public Patient registerPatient(PatientRegisterCommand cmd) {
        validateRegister(cmd);
        try {
            Patient probe = new Patient();
            probe.setDocument(parseLong(cmd.document));
            if (patientPort.findByDocument(probe) != null) {
                throw new ConflictException("Document already exists");
            }
        } catch (Exception e) { throw new RuntimeException(e); }

        Patient p = new Patient();
        p.setDocument(parseLong(cmd.document));
        p.setFullName(cmd.fullName);
        p.setBirth(java.sql.Date.valueOf(cmd.birthDate));
        p.setGender(cmd.gender);
        p.setAddress(cmd.address);
        p.setTelephone(parsePhone(cmd.phone));
        p.setEmail(cmd.email);
        if (cmd.username != null) {
            p.setUserName(cmd.username);
        }
        if (cmd.password != null) {
            p.setPassword(passwordEncoder.encode(cmd.password));
        }
        if (cmd.emergencyContact != null) {
            p.setEmergencyFirstName(cmd.emergencyContact.name);
            p.setRelationShip(cmd.emergencyContact.relationship);
            p.setEmergencyContact(parsePhone(cmd.emergencyContact.phone));
        }
        if (cmd.insurancePolicy != null) {
            p.setCompanyName(cmd.insurancePolicy.company);
            p.setCompanyNumber(parseLong(cmd.insurancePolicy.policyNumber));
            p.setStatus(cmd.insurancePolicy.active);
            p.setValidity(java.sql.Date.valueOf(cmd.insurancePolicy.validUntil));
        }

        try { patientPort.save(p); } catch (Exception e) { throw new RuntimeException(e); }
        return p;
    }

    public Patient updatePatient(String document, PatientUpdateCommand cmd) {
        Patient existing;
        try {
            Patient probe = new Patient();
            probe.setDocument(parseLong(document));
            existing = patientPort.findByDocument(probe);
        } catch (Exception e) { throw new RuntimeException(e); }
        if (existing == null) throw new NotFoundException("Patient not found");

        if (cmd.fullName != null) existing.setFullName(cmd.fullName);
        if (cmd.birthDate != null) {
            validateAge150(cmd.birthDate);
            existing.setBirth(java.sql.Date.valueOf(cmd.birthDate));
        }
        if (cmd.gender != null) existing.setGender(cmd.gender);
        if (cmd.address != null) existing.setAddress(cmd.address);
        if (cmd.phone != null) {
            validatePatientPhone(cmd.phone);
            existing.setTelephone(parsePhone(cmd.phone));
        }
        if (cmd.email != null) {
            validateEmail(cmd.email);
            existing.setEmail(cmd.email);
        }
        if (cmd.username != null) {
            validateUsername(cmd.username);
            existing.setUserName(cmd.username);
        }
        if (cmd.password != null) {
            validateStrongPassword(cmd.password);
            existing.setPassword(passwordEncoder.encode(cmd.password));
        }

        try { patientPort.update(existing); } catch (Exception e) { throw new RuntimeException(e); }
        return existing;
    }

    public Patient assignEmergencyContact(String document, EmergencyContactDto dto) {
        validatePatientPhone(dto.phone);
        Patient p;
        try {
            Patient probe = new Patient();
            probe.setDocument(parseLong(document));
            p = patientPort.findByDocument(probe);
        } catch (Exception e) { throw new RuntimeException(e); }
        if (p == null) throw new NotFoundException("Patient not found");
        p.setEmergencyFirstName(dto.name);
        p.setRelationShip(dto.relationship);
        p.setEmergencyContact(parsePhone(dto.phone));
        try { patientPort.update(p); } catch (Exception e) { throw new RuntimeException(e); }
        return p;
    }

    public Patient assignInsurancePolicy(String document, InsurancePolicyDto dto) {
        if (dto.validUntil == null) throw new ValidationException("Valid until is required");
        Patient p;
        try {
            Patient probe = new Patient();
            probe.setDocument(parseLong(document));
            p = patientPort.findByDocument(probe);
        } catch (Exception e) { throw new RuntimeException(e); }
        if (p == null) throw new NotFoundException("Patient not found");
        p.setCompanyName(dto.company);
        p.setCompanyNumber(parseLong(dto.policyNumber));
        p.setStatus(dto.active);
        p.setValidity(java.sql.Date.valueOf(dto.validUntil));
        try { patientPort.update(p); } catch (Exception e) { throw new RuntimeException(e); }
        return p;
    }

    public Patient getPatientByDocument(String document) {
        try {
            Patient probe = new Patient();
            probe.setDocument(parseLong(document));
            Patient found = patientPort.findByDocument(probe);
            if (found == null) throw new NotFoundException("Patient not found");
            return found;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public List<Patient> searchPatients(String query) {
        try {
            Patient probe = new Patient();
            probe.setFullName(query);
            return patientPort.searchByName(probe);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private void validateRegister(PatientRegisterCommand cmd) {
        if (cmd == null) throw new ValidationException("Command is required");
        if (isBlank(cmd.document) || isBlank(cmd.fullName) || cmd.birthDate == null
                || isBlank(cmd.phone)) throw new ValidationException("Missing required fields");
        validateAge150(cmd.birthDate);
        validatePatientPhone(cmd.phone);
        if (cmd.email != null) validateEmail(cmd.email);
        if (cmd.username != null) validateUsername(cmd.username);
        if (cmd.password != null) validateStrongPassword(cmd.password);
        if (cmd.emergencyContact != null) validatePatientPhone(cmd.emergencyContact.phone);
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static long parseLong(String v) { try { return Long.parseLong(v); } catch (Exception e) { throw new ValidationException("Invalid number"); } }
    private static int parsePhone(String v) { try { return Integer.parseInt(v); } catch (Exception e) { throw new ValidationException("Invalid phone"); } }

    public static class PatientRegisterCommand {
        public String document;
        public String fullName;
        public LocalDate birthDate;
        public String gender;
        public String address;
        public String phone;
        public String email;
        public String username;
        public String password;
        public EmergencyContactDto emergencyContact;
        public InsurancePolicyDto insurancePolicy;
    }

    public static class PatientUpdateCommand {
        public String fullName;
        public LocalDate birthDate;
        public String gender;
        public String address;
        public String phone;
        public String email;
        public String username;
        public String password;
    }

    public static class EmergencyContactDto {
        public String name;
        public String relationship;
        public String phone;
    }

    public static class InsurancePolicyDto {
        public String company;
        public String policyNumber;
        public boolean active;
        public LocalDate validUntil;
    }
}


