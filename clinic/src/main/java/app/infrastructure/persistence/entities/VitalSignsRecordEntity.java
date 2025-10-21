package app.infrastructure.persistence.entities;

import java.sql.Timestamp;

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
 * Entidad JPA que representa un registro de signos vitales tomado por una
 * enfermera. Incluye la relación con la enfermera y el paciente, la
 * fecha y hora del registro, la presión arterial, la temperatura, el
 * pulso y el nivel de oxígeno en la sangre.
 */
@Entity
@Table(name = "vital_signs_records")
public class VitalSignsRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id", nullable = false)
    private EmployeeEntity nurse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(nullable = false)
    private Timestamp dateTime;

    @Column(nullable = false, length = 20)
    private String bloodPressure;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Integer pulse;

    @Column(nullable = false)
    private Integer oxygenLevel;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public EmployeeEntity getNurse() {
        return nurse;
    }
    public void setNurse(EmployeeEntity nurse) {
        this.nurse = nurse;
    }
    public PatientEntity getPatient() {
        return patient;
    }
    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
    public Timestamp getDateTime() {
        return dateTime;
    }
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
    public String getBloodPressure() {
        return bloodPressure;
    }
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public Integer getPulse() {
        return pulse;
    }
    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }
    public Integer getOxygenLevel() {
        return oxygenLevel;
    }
    public void setOxygenLevel(Integer oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }
}