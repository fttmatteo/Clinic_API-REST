package app.domain.model;

import java.sql.Date;

import app.domain.model.enums.Gender;

/**
 * Clase base para representar a cualquier persona dentro del sistema de la clínica.
 * Define los atributos comunes como el identificador, el documento de identidad,
 * el nombre completo, fecha de nacimiento, dirección, teléfono, correo
 * electrónico y género. Todas las clases que representen a un paciente o
 * empleado deben extender esta clase para heredar estos atributos.
 */
public class Person {

    private Long id;
    private Long document;
    private String fullName;
    private Date birthDate;
    private String address;
    private String phone;
    private String email;
    private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}