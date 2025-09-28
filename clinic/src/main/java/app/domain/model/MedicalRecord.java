package app.domain.model;

import java.sql.Timestamp;

/**
 * Representa la historia clínica de un paciente. Cada registro se compone de
 * los datos de la consulta (fecha y hora, motivo, síntomas, diagnóstico)
 * junto con el médico que atendió al paciente y, opcionalmente, la orden
 * médica asociada.
 */
public class MedicalRecord {
    private long id;
    private Patient patient;
    private Employee doctor;
    /**
     * Fecha y hora de la consulta (clave de la historia clínica). Se emplea
     * {@link java.sql.Timestamp} para almacenar tanto la fecha como la hora
     * exacta del registro y facilitar su almacenamiento en la base de datos.
     */
    private Timestamp dateTime;
    private String motive;
    private String symptoms;
    private String diagnosis;
    private MedicalOrder medicalOrder;
    private boolean active;

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