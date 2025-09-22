// VitalSignsMapper.java
package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.VitalSigns;
import app.infrastructure.persistence.entities.VitalSignsEntity;
import app.infrastructure.persistence.entities.VitalSignsKey;

@Component
public class VitalSignsMapper {
  public VitalSigns toDomain(VitalSignsEntity e){
    if(e==null) return null;
    VitalSigns d = new VitalSigns();
    d.setPatientDocument(e.getVitalId().getPatientDocument());
    d.setRegistrationDate(e.getVitalId().getRegistrationDate());
    d.setBloodPressure(e.getBloodPressure());
    d.setTemperature(e.getTemperature());
    d.setPulse(e.getPulse());
    d.setOxygenSaturation(e.getOxygenSaturation());
    return d;
  }
  public VitalSignsEntity toEntity(VitalSigns d){
    VitalSignsEntity e = new VitalSignsEntity();
    e.setVitalId(new VitalSignsKey(d.getPatientDocument(), d.getRegistrationDate()));
    e.setBloodPressure(d.getBloodPressure());
    e.setTemperature(d.getTemperature());
    e.setPulse(d.getPulse());
    e.setOxygenSaturation(d.getOxygenSaturation());
    return e;
  }
}
