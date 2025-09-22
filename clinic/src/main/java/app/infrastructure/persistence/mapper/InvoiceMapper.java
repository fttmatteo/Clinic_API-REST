// InvoiceMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Invoice;
import app.infrastructure.persistence.entities.InvoiceEntity;

@Component
public class InvoiceMapper {
  public Invoice toDomain(InvoiceEntity e){
    if(e==null) return null;
    Invoice d = new Invoice();
    d.setInvoiceId(e.getInvoiceId()==null?0L:e.getInvoiceId());
    d.setPatientDocument(e.getPatientDocument());
    d.setDoctorName(e.getDoctorName());
    d.setServiceDescription(e.getServiceDescription());
    d.setTotalPatient(e.getTotalPatient());
    d.setCopayment(e.getCopayment());
    d.setTotalInsurance(e.getTotalInsurance());
    d.setEndDatePolicy(e.getEndDatePolicy());
    return d;
  }
  public InvoiceEntity toEntity(Invoice d){
    InvoiceEntity e = new InvoiceEntity();
    if (d.getInvoiceId() > 0) e.setInvoiceId(d.getInvoiceId());
    e.setPatientDocument(d.getPatientDocument());
    e.setDoctorName(d.getDoctorName());
    e.setServiceDescription(d.getServiceDescription());
    e.setTotalPatient(d.getTotalPatient());
    e.setCopayment(d.getCopayment());
    e.setTotalPayable(d.getTotalInsurance());
    e.setEndDatePolicy(d.getEndDatePolicy());
    return e;
  }
}
