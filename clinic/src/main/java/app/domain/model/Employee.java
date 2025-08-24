package app.domain.model;

import java.sql.Date;

public class Employee {
    private long id;
    private String fullName;
    private int document;
    private String email;
    private String phone;
    private Date birthDate;
    private String address;
    private User role;
    private User username;
    private User password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getRole() {
        return role;
    }

    public void setRole(User role) {
        this.role = role;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public User getPassword() {
        return password;
    }

    public void setPassword(User password) {
        this.password = password;
    }
}
