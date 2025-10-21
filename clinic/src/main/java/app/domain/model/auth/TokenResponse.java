package app.domain.model.auth;

/**
 * Respuesta devuelta al cliente tras un inicio de sesión exitoso. Contiene
 * el token JWT generado para el usuario autenticado.
 */
public class TokenResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}