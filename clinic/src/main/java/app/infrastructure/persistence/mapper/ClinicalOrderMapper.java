// ClinicalOrderMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.ClinicalOrder;
import app.infrastructure.persistence.entities.ClinicalOrderEntity;

@Component
public class ClinicalOrderMapper {
  public ClinicalOrder toDomain(ClinicalOrderEntity e){
    if(e==null) return null;
    ClinicalOrder d = new ClinicalOrder();
    d.setNumberOrder(e.getNumberOrder());
    d.setPatientDocument(e.getPatientDocument());
    d.setDoctorDocument(e.getDoctorDocument());
    d.setCreationDate(e.getCreationDate());
    return d;
  }
  public ClinicalOrderEntity toEntity(ClinicalOrder d){
    ClinicalOrderEntity e = new ClinicalOrderEntity();
    e.setNumberOrder(d.getNumberOrder());
    e.setPatientDocument(d.getPatientDocument());
    e.setDoctorDocument(d.getDoctorDocument());
    e.setCreationDate(d.getCrationDate());
    return e;
  }
}
