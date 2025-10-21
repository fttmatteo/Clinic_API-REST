package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.model.auth.AuthCredentials;
import app.domain.model.auth.TokenResponse;
import app.domain.services.AuthenticationService;

/**
 * Caso de uso para el proceso de inicio de sesión. Orquesta la
 * autenticación delegando en el servicio de autenticación.
 */
@Component
public class LoginUseCase {
    @Autowired
    private AuthenticationService authenticationService;

    public TokenResponse login(AuthCredentials credentials) throws Exception {
        return authenticationService.authenticate(credentials);
    }
}