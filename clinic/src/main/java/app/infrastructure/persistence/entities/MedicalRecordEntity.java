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
 * Entidad JPA que representa la historia clínica de un paciente. Aunque
 * en el diseño original esta información podría almacenarse en una
 * base de datos NoSQL, para fines de este proyecto se persiste en una
 * tabla relacional. Incluye el paciente, el médico que atendió, la
 * fecha y hora de la consulta, el motivo, los síntomas, el
 * diagnóstico, un indicador de actividad y una relación opcional con
 * una orden médica.
 */
@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity {
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
    private Timestamp dateTime;

    @Column(nullable = false, length = 500)
    private String motive;

    @Column(nullable = false, length = 500)
    private String symptoms;

    @Column(nullable = false, length = 500)
    private String diagnosis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private MedicalOrderEntity medicalOrder;

    @Column(nullable = false)
    private Boolean active;

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
    public MedicalOrderEntity getMedicalOrder() {
        return medicalOrder;
    }
    public void setMedicalOrder(MedicalOrderEntity medicalOrder) {
        this.medicalOrder = medicalOrder;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}