package app.domain.services;

import app.domain.common.exceptions.ConflictException;
import app.domain.common.exceptions.NotFoundException;
import app.domain.common.exceptions.ValidationException;
import app.domain.model.User;
import app.domain.ports.UserPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import static app.domain.services.validation.Validators.*;

public class UserService {

    private final UserPort userPort;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserPort userPort) {
        this.userPort = Objects.requireNonNull(userPort);
    }

    public User createUser(UserCreateCommand cmd) {
        validateCreate(cmd);

        try {
            User probe = new User();
            probe.setDocument(parseLong(cmd.document));
            if (userPort.findByDocument(probe) != null) {
                throw new ConflictException("Document already exists");
            }
            probe.setUserName(cmd.username);
            if (userPort.findByUserName(probe) != null) {
                throw new ConflictException("Username already exists");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = new User();
        user.setFullName(cmd.fullName);
        user.setDocument(parseLong(cmd.document));
        user.setEmail(cmd.email);
        user.setTelephone(parseInt(cmd.phone));
        user.setBirth(java.sql.Date.valueOf(cmd.birthDate));
        user.setAddress(cmd.address);
        user.setUserName(cmd.username);
        user.setPassword(passwordEncoder.encode(cmd.password));

        try {
            userPort.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User updateUser(String document, UserUpdateCommand cmd) {
        User existing;
        try {
            User probe = new User();
            probe.setDocument(parseLong(document));
            existing = userPort.findByDocument(probe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (existing == null) throw new NotFoundException("User not found");

        if (cmd.email != null) validateEmail(cmd.email);
        if (cmd.phone != null) validateUserPhone(cmd.phone);
        if (cmd.address != null) validateAddress(cmd.address);
        if (cmd.birthDate != null) validateAge150(cmd.birthDate);
        if (cmd.username != null) validateUsername(cmd.username);
        if (cmd.password != null) validateStrongPassword(cmd.password);

        if (cmd.username != null && !cmd.username.equals(existing.getUserName())) {
            try {
                User probe = new User();
                probe.setUserName(cmd.username);
                if (userPort.findByUserName(probe) != null) throw new ConflictException("Username already exists");
            } catch (Exception e) { throw new RuntimeException(e); }
        }

        if (cmd.fullName != null) existing.setFullName(cmd.fullName);
        if (cmd.email != null) existing.setEmail(cmd.email);
        if (cmd.phone != null) existing.setTelephone(parseInt(cmd.phone));
        if (cmd.birthDate != null) existing.setBirth(java.sql.Date.valueOf(cmd.birthDate));
        if (cmd.address != null) existing.setAddress(cmd.address);
        if (cmd.username != null) existing.setUserName(cmd.username);
        if (cmd.password != null) existing.setPassword(passwordEncoder.encode(cmd.password));

        try {
            userPort.update(existing);
        } catch (Exception e) { throw new RuntimeException(e); }
        return existing;
    }

    public User getUserByDocument(String document) {
        try {
            User probe = new User();
            probe.setDocument(parseLong(document));
            User u = userPort.findByDocument(probe);
            if (u == null) throw new NotFoundException("User not found");
            return u;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void deleteUserByDocument(String document) {
        try {
            User probe = new User();
            probe.setDocument(parseLong(document));
            userPort.delete(probe);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private void validateCreate(UserCreateCommand cmd) {
        if (cmd == null) throw new ValidationException("Command is required");
        if (isBlank(cmd.document) || isBlank(cmd.fullName) || isBlank(cmd.username) || isBlank(cmd.password)) {
            throw new ValidationException("Missing required fields");
        }
        if (cmd.email != null) validateEmail(cmd.email);
        validateUserPhone(cmd.phone);
        validateAddress(cmd.address);
        validateAge150(cmd.birthDate);
        validateUsername(cmd.username);
        validateStrongPassword(cmd.password);
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static long parseLong(String v) { try { return Long.parseLong(v); } catch (Exception e) { throw new ValidationException("Invalid document"); } }
    private static int parseInt(String v) { if (v == null) return 0; try { return Integer.parseInt(v); } catch (Exception e) { throw new ValidationException("Invalid phone"); } }

    public static class UserCreateCommand {
        public String fullName;
        public String document;
        public String email;
        public String phone;
        public LocalDate birthDate;
        public String address;
        public String username;
        public String password;
    }

    public static class UserUpdateCommand {
        public String fullName;
        public String email;
        public String phone;
        public LocalDate birthDate;
        public String address;
        public String username;
        public String password;
    }
}


