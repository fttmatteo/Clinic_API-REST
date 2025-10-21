package app.infrastructure.persistence.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa una factura emitida por la clínica. Contiene
 * la relación con el paciente y opcionalmente con el médico y la orden
 * médica, así como la información del producto/servicio facturado,
 * el monto, si corresponde a un medicamento y la fecha de emisión.
 */
@Entity
@Table(name = "invoices")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private EmployeeEntity doctor;

    @Column(nullable = false, length = 150)
    private String productName;

    @Column(nullable = false)
    private Double productAmount;

    @Column(nullable = false)
    private Boolean medicine;

    @Column(nullable = false)
    private Double copay;

    @Column(nullable = false)
    private Double billedToInsurer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private MedicalOrderEntity order;

    @Column(nullable = false)
    private Date date;

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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getProductAmount() {
        return productAmount;
    }
    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }
    public Boolean getMedicine() {
        return medicine;
    }
    public void setMedicine(Boolean medicine) {
        this.medicine = medicine;
    }

    public Double getCopay() {
        return copay;
    }

    public void setCopay(Double copay) {
        this.copay = copay;
    }

    public Double getBilledToInsurer() {
        return billedToInsurer;
    }

    public void setBilledToInsurer(Double billedToInsurer) {
        this.billedToInsurer = billedToInsurer;
    }
    public MedicalOrderEntity getOrder() {
        return order;
    }
    public void setOrder(MedicalOrderEntity order) {
        this.order = order;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}