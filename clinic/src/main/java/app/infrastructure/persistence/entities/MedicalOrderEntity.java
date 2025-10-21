package app.infrastructure.persistence.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa una orden médica. Contiene la relación con
 * el paciente, el médico que la creó, la fecha y la lista de ítems
 * asociados a dicha orden. Las reglas de negocio se implementan en
 * las capas superiores.
 */
@Entity
@Table(name = "medical_orders")
public class MedicalOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private EmployeeEntity doctor;

    @Column(nullable = false)
    private Date creationDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItemEntity> items = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PatientEntity getPatient() {
        return patient;
    }
    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
    public EmployeeEntity getDoctor() {
        return doctor;
    }
    public void setDoctor(EmployeeEntity doctor) {
        this.doctor = doctor;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public List<OrderItemEntity> getItems() {
        return items;
    }
    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }
}