package app.domain.model;

import java.sql.Date;

public class ClinicalHistory {
    private Date date;
    private Employee document;
    private String motive;
    private String symptoms;
    private String diagnosis;
    private Medicine orderId;
    private Medicine medicineId;
    private Medicine dose;
    private Medicine treatmentDuration;
    private Medicine item;
    private ProcedureMedication orderIdProcedure;
    private ProcedureMedication idOrderProcedure;
    private ProcedureMedication quantity;
    private ProcedureMedication frequency;
    private ProcedureMedication specialistRequired;
    private ProcedureMedication specialistId;
    private ProcedureMedication itemProcedure;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getDocument() {
        return document;
    }

    public void setDocument(Employee document) {
        this.document = document;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Medicine getOrderId() {
        return orderId;
    }

    public void setOrderId(Medicine orderId) {
        this.orderId = orderId;
    }

    public Medicine getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Medicine medicineId) {
        this.medicineId = medicineId;
    }

    public Medicine getDose() {
        return dose;
    }

    public void setDose(Medicine dose) {
        this.dose = dose;
    }

    public Medicine getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(Medicine treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public Medicine getItem() {
        return item;
    }

    public void setItem(Medicine item) {
        this.item = item;
    }

    public ProcedureMedication getOrderIdProcedure() {
        return orderIdProcedure;
    }

    public void setOrderIdProcedure(ProcedureMedication orderIdProcedure) {
        this.orderIdProcedure = orderIdProcedure;
    }

    public ProcedureMedication getIdOrderProcedure() {
        return idOrderProcedure;
    }

    public void setIdOrderProcedure(ProcedureMedication idOrderProcedure) {
        this.idOrderProcedure = idOrderProcedure;
    }

    public ProcedureMedication getQuantity() {
        return quantity;
    }

    public void setQuantity(ProcedureMedication quantity) {
        this.quantity = quantity;
    }

    public ProcedureMedication getFrequency() {
        return frequency;
    }

    public void setFrequency(ProcedureMedication frequency) {
        this.frequency = frequency;
    }

    public ProcedureMedication getSpecialistRequired() {
        return specialistRequired;
    }

    public void setSpecialistRequired(ProcedureMedication specialistRequired) {
        this.specialistRequired = specialistRequired;
    }

    public ProcedureMedication getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(ProcedureMedication specialistId) {
        this.specialistId = specialistId;
    }

    public ProcedureMedication getItemProcedure() {
        return itemProcedure;
    }

    public void setItemProcedure(ProcedureMedication itemProcedure) {
        this.itemProcedure = itemProcedure;
    }
}
