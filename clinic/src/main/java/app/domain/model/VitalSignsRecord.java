package app.domain.model;

import java.sql.Timestamp;

/**
 * Representa un registro de signos vitales tomados por una enfermera. Incluye
 * los valores de presión arterial, temperatura, pulso y nivel de oxígeno en
 * sangre, junto con el paciente y la enfermera responsables. Cada registro
 * posee una marca de tiempo que indica cuándo se tomaron los signos.
 */
public class VitalSignsRecord {
    private Long id;
    private Patient patient;
    private Employee nurse;
    private Timestamp dateTime;
    private String bloodPressure;
    private Double temperature;
    private Integer pulse;
    private Integer oxygenLevel;

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

    public Employee getNurse() {
        return nurse;
    }

    public void setNurse(Employee nurse) {
        this.nurse = nurse;
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