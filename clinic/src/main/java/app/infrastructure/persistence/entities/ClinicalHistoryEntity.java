package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="clinical_history")
public class ClinicalHistoryEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long historyId;

  @Column(nullable=false) private int patientDocument;
  @Column(nullable=false) private Date attentionDate;
  @Column(nullable=false) private int doctorDocument;

  @Column(length=250) private String motive;
  @Column(length=500) private String symptoms;
  @Column(length=500) private String diagnosis;

  public Long getHistoryId() { return historyId; }
  public void setHistoryId(Long historyId) { this.historyId = historyId; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public Date getAttentionDate() { return attentionDate; }
  public void setAttentionDate(Date attentionDate) { this.attentionDate = attentionDate; }
  public int getDoctorDocument() { return doctorDocument; }
  public void setDoctorDocument(int doctorDocument) { this.doctorDocument = doctorDocument; }
  public String getMotive() { return motive; }
  public void setMotive(String motive) { this.motive = motive; }
  public String getSymptoms() { return symptoms; }
  public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
  public String getDiagnosis() { return diagnosis; }
  public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
