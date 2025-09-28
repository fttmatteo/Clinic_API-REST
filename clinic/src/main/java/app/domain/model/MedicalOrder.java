package app.domain.model;

import java.time.LocalDate;
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
    /** Número identificador único de la orden (máximo 6 dígitos). */
    private long id;
    /** Paciente al que se aplica la orden. */
    private Patient patient;
    /** Médico que prescribe la orden. */
    private Employee doctor;
    /** Fecha de creación de la orden. */
    private LocalDate creationDate;
    /** Lista de ítems asociados a la orden. */
    private List<OrderItem> items = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}