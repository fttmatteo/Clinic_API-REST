package app.domain.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una orden médica que prescribe medicamentos, procedimientos o
 * ayudas diagnósticas para un paciente. Una orden puede contener uno o
 * varios ítems dependiendo del tratamiento necesario. Cada orden es
 * identificada por un número único y está asociada a un paciente y a un
 * médico. Las reglas de negocio garantizan que una orden no mezcle ayudas
 * diagnósticas con medicamentos o procedimientos, y que los números de ítem
 * dentro de una orden sean únicos.
 */
public class MedicalOrder {
    private Long id;
    private String orderNumber;
    private Patient patient;
    private Employee doctor;
    private Date creationDate;
    private List<OrderItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public void setDoctor(Employee doctor) {
        this.doctor = doctor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}