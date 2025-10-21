package app.domain.model;

import app.domain.model.enums.Role;

/**
 * Representa a un empleado de la clínica. Hereda los atributos comunes de
 * {@link Person} y añade información específica como el nombre de usuario,
 * la contraseña y el rol que desempeña dentro de la clínica. Dependiendo del
 * rol asignado, el empleado tendrá permisos diferentes sobre la aplicación.
 */
public class Employee extends Person {
    private String userName;
    private String password;
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}