package app.domain.model;

import java.sql.Date;

/**
 * Representa una factura emitida por la clínica. Una factura incluye al
 * paciente, al médico tratante, la orden médica asociada (si corresponde),
 * el nombre del producto o servicio facturado y el monto total. Cuando se
 * factura un medicamento, siempre debe existir una orden médica asociada.
 */
public class Invoice {
    private Long id;
    private Patient patient;
    private Employee doctor;
    private MedicalOrder order;
    private String productName;
    private double productAmount;
    private Date date;
    private boolean medicine;
    private double copay;
    private double billedToInsurer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public MedicalOrder getOrder() {
        return order;
    }

    public void setOrder(MedicalOrder order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isMedicine() {
        return medicine;
    }

    public void setMedicine(boolean medicine) {
        this.medicine = medicine;
    }

    public double getCopay() {
        return copay;
    }

    public void setCopay(double copay) {
        this.copay = copay;
    }

    public double getBilledToInsurer() {
        return billedToInsurer;
    }

    public void setBilledToInsurer(double billedToInsurer) {
        this.billedToInsurer = billedToInsurer;
    }
}