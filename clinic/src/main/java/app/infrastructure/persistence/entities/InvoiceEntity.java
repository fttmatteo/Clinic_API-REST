package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="invoices")
public class InvoiceEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private int patientDocument;
  @Column(nullable=false, length=120) private String professionalName;
  @Column(length=1000) private String clinicalDetail;

  @Column(nullable=false) private long totalService;
  @Column(nullable=false) private long copay;
  @Column(nullable=false) private long totalPayable;

  @Column(nullable=false) private Date invoiceDate;

  // getters/setters...
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public int getPatientDocument() { return patientDocument; }
  public void setPatientDocument(int patientDocument) { this.patientDocument = patientDocument; }
  public String getProfessionalName() { return professionalName; }
  public void setProfessionalName(String professionalName) { this.professionalName = professionalName; }
  public String getClinicalDetail() { return clinicalDetail; }
  public void setClinicalDetail(String clinicalDetail) { this.clinicalDetail = clinicalDetail; }
  public long getTotalService() { return totalService; }
  public void setTotalService(long totalService) { this.totalService = totalService; }
  public long getCopay() { return copay; }
  public void setCopay(long copay) { this.copay = copay; }
  public long getTotalPayable() { return totalPayable; }
  public void setTotalPayable(long totalPayable) { this.totalPayable = totalPayable; }
  public Date getInvoiceDate() { return invoiceDate; }
  public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }
}
