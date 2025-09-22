// ClinicalHistoryMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.ClinicalHistory;
import app.infrastructure.persistence.entities.ClinicalHistoryEntity;

@Component
public class ClinicalHistoryMapper {
  public ClinicalHistory toDomain(ClinicalHistoryEntity saved){
    if(saved==null) return null;
    ClinicalHistory d = new ClinicalHistory();
    d.setPatientDocument(saved.getPatientDocument());
    d.setAttentionDate(saved.getAttentionDate());
    d.setDoctorDocument(saved.getDoctorDocument());
    d.setMotive(saved.getMotive());
    d.setSymptoms(saved.getSymptoms());
    d.setDiagnosis(saved.getDiagnosis());
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
