package app.domain.model.auth;

/**
 * Representa las credenciales de autenticación proporcionadas por el
 * usuario en el proceso de inicio de sesión. Contiene el nombre de
 * usuario y la contraseña sin cifrar.
 */
public class AuthCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}