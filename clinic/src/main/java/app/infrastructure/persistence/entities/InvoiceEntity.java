package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="invoices")
public class InvoiceEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long invoiceId;

  @Column(nullable=false) private int patientDocument;
  @Column(nullable=false, length=120) private String doctorName;
  @Column(length=1000) private String serviceDescription;

  @Column(nullable=false) private long copayment;
  @Column(nullable=false) private long totalPatient;
  @Column(nullable=false) private long totalInsurance;

  @Column(nullable=false) private Date endDatePolicy;

  public Long getInvoiceId() { return invoiceId; }
  public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public String getDoctorName() { return doctorName; }
  public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
  public String getServiceDescription() { return serviceDescription; }
  public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }
  public long getCopayment() { return copayment; }
  public void setCopayment(long copayment) { this.copayment = copayment; }
  public long getTotalPatient() { return totalPatient; }
  public void setTotalPatient(long totalPatient) { this.totalPatient = totalPatient; }
  public long getTotalInsurance() { return totalInsurance; }
  public void setTotalPayable(long totalPayable) { this.totalInsurance = totalPayable; }
  public Date getEndDatePolicy() { return endDatePolicy; }
  public void setEndDatePolicy(Date endDatePolicy) { this.endDatePolicy = endDatePolicy; }
}
