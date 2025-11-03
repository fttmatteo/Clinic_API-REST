package app.domain.model;

import java.sql.Timestamp;

/**
 * Representa una cita programada entre un paciente y un médico. Cada cita
 * contiene información sobre la fecha y hora en que se llevará a cabo,
 * el paciente y el médico que participarán y el estado de la cita.
 * El estado permite marcar citas como programadas, canceladas o completadas.
 */
public class Appointment {
    private Long id;
    private Patient patient;
    private Employee doctor;
    private Timestamp dateTime;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}