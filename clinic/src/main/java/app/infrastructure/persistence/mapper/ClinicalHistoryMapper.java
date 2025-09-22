// ClinicalHistoryMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.ClinicalHistory;
import app.infrastructure.persistence.entities.ClinicalHistoryEntity;

@Component
public class ClinicalHistoryMapper {
  public ClinicalHistory toDomain(ClinicalHistory e){
    if(e==null) return null;
    ClinicalHistory d = new ClinicalHistory();
    d.setPatientDocument(e.getPatientDocument());
    d.setAttentionDate(e.getAttentionDate());
    d.setDoctorDocument(e.getDoctorDocument());
    d.setMotive(e.getMotive());
    d.setSymptoms(e.getSymptoms());
    d.setDiagnosis(e.getDiagnosis());
    return d;
  }
  public ClinicalHistoryEntity toEntity(ClinicalHistory d){
    ClinicalHistoryEntity e = new ClinicalHistoryEntity();
    e.setPatientDocument(d.getPatientDocument());
    e.setAttentionDate(d.getAttentionDate());
    e.setDoctorDocument(d.getDoctorDocument());
    e.setMotive(d.getMotive());
    e.setSymptoms(d.getSymptoms());
    e.setDiagnosis(d.getDiagnosis());
    return e;
  }
}
