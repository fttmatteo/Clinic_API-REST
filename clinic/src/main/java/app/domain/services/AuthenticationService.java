package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;
import app.domain.model.Employee;
import app.domain.ports.AuthenticationPort;
import app.domain.ports.EmployeePort;

/**
 * Servicio de dominio encargado de la autenticación de usuarios. Valida
 * las credenciales ingresadas por el usuario y delega la generación del
 * token al puerto de autenticación.
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationPort authenticationPort;

    @Autowired
    private EmployeePort employeePort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenResponse authenticate(AuthCredentials credentials) throws Exception {
        Employee query = new Employee();
        query.setUserName(credentials.getUsername());
        Employee employee = employeePort.findByUserName(query);
        if (employee == null) {
            throw new BusinessException("Usuario no encontrado");
        }
        if (!passwordEncoder.matches(credentials.getPassword(), employee.getPassword())) {
            throw new BusinessException("Contraseña incorrecta");
        }
        return authenticationPort.authenticate(credentials, String.valueOf(employee.getRole()));
    }
}