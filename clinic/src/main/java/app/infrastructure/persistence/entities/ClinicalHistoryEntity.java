package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="clinical_history")
public class ClinicalHistoryEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private int patientDocument;
  @Column(nullable=false) private Date date;
  @Column(nullable=false) private int professionalDocument;

  @Column(length=250) private String motive;
  @Column(length=500) private String symptoms;
  @Column(length=500) private String diagnosis;

  // getters/setters...
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }
  public int getProfessionalDocument() { return professionalDocument; }
  public void setProfessionalDocument(int professionalDocument) { this.professionalDocument = professionalDocument; }
  public String getMotive() { return motive; }
  public void setMotive(String motive) { this.motive = motive; }
  public String getSymptoms() { return symptoms; }
  public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
  public String getDiagnosis() { return diagnosis; }
  public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
