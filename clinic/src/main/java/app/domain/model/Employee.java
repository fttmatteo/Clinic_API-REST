package app.domain.model;

public class Employee extends Person {
    private Employee role;
    private Employee username;
    private Employee password;

    public Employee getRole() {
        return role;
    }
    public void setRole(Employee role) {
        this.role = role;
    }
    public Employee getUsername() {
        return username;
    }
    public void setUsername(Employee username) {
        this.username = username;
    }
    public Employee getPassword() {
        return password;
    }
    public void setPassword(Employee password) {
        this.password = password;
    }
}
