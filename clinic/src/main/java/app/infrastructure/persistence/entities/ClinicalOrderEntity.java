package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="clinical_orders")
public class ClinicalOrderEntity {

  @Id
  @Column(nullable=false)
  private int numberOrder;

  @Column(nullable=false)
  private int patientDocument;

  @Column(nullable=false)
  private int doctorDocument;

  @Column(nullable=false)
  private Date creationDate;

  public int getNumberOrder() { return numberOrder; }
  public void setNumberOrder(int numberOrder) { this.numberOrder = numberOrder; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public int getDoctorDocument() { return doctorDocument; }
  public void setDoctorDocument(int doctorDocument) { this.doctorDocument = doctorDocument; }
  public Date getCreationDate() { return creationDate; }
  public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
}
