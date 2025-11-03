package app.domain.model;

import java.sql.Timestamp;

/**
 * Representa la historia clínica de un paciente. Cada registro se compone de
 * los datos de la consulta (fecha y hora, motivo, síntomas, diagnóstico)
 * junto con el médico que atendió al paciente y, opcionalmente, la orden
 * médica asociada.
 */
public class MedicalRecord {
    private Long id;
    private Patient patient;
    private Employee doctor;
    private Timestamp dateTime;
    private String motive;
    private String symptoms;
    private String diagnosis;
    private MedicalOrder medicalOrder;
    private boolean active;

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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
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

    public MedicalOrder getMedicalOrder() {
        return medicalOrder;
    }

    public void setMedicalOrder(MedicalOrder medicalOrder) {
        this.medicalOrder = medicalOrder;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}