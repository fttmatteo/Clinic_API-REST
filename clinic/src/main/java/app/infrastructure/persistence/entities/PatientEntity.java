package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patients",
       uniqueConstraints = {
         @UniqueConstraint(name="uk_patient_document", columnNames = "document"),
         @UniqueConstraint(name="uk_patient_email", columnNames = "email")
       })
public class PatientEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long patientId;

  @Column(nullable=false) private int patientDocument;
  @Column(nullable=false, length=120) private String fullName;
  @Column(nullable=false) private Date birthDate;
  @Column(length=20) private String gender;
  @Column(length=100) private String address;
  @Column(nullable=false) private int phoneNumber;
  @Column(nullable=false, length=255) private String email;

  @Column(length=80) private String emergencyFirstName;
  @Column(length=80) private String emergencyLastName;
  @Column(length=40) private String relationShip;
  @Column(length=10) private String emergencyPhone;

  @Column(length=100) private String companyName;
  private long numberPolicy;
  private boolean statusPolicy;
  private int validityDaysPolicy;
  private Date endDatePolicy;

  public Long getPatientId() { return patientId; }
  public void setPatientId(Long patientId) { this.patientId = patientId; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public Date getBirthDate() { return birthDate; }
  public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
  public String getGender() { return gender; }
  public void setGender(String gender) { this.gender = gender; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public int getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getEmergencyFirstName() { return emergencyFirstName; }
  public void setEmergencyFirstName(String emergencyFirstName) { this.emergencyFirstName = emergencyFirstName; }
  public String getEmergencyLastName() { return emergencyLastName; }
  public void setEmergencyLastName(String emergencyLastName) { this.emergencyLastName = emergencyLastName; }
  public String getRelationShip() { return relationShip; }
  public void setRelationShip(String relationShip) { this.relationShip = relationShip; }
  public String getEmergencyPhone() { return emergencyPhone; }
  public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }
  public String getCompanyName() { return companyName; }
  public void setCompanyName(String companyName) { this.companyName = companyName; }
  public long getNumberPolicy() { return numberPolicy; }
  public void setNumberPolicy(long numberPolicy) { this.numberPolicy = numberPolicy; }
  public boolean isStatusPolicy() { return statusPolicy; }
  public void setStatusPolicy(boolean statusPolicy) { this.statusPolicy = statusPolicy; }
  public int getValidityDaysPolicy() { return validityDaysPolicy; }
  public void setValidityDaysPolicy(int validityDaysPolicy) { this.validityDaysPolicy = validityDaysPolicy; }
  public Date getEndDatePolicy() { return endDatePolicy; }
  public void setEndDatePolicy(Date endDatePolicy) { this.endDatePolicy = endDatePolicy; }
}
