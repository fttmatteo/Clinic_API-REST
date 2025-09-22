package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.Patient;
import app.domain.model.MedicalInsurance;
import app.infrastructure.persistence.entities.PatientEntity;

@Component
public class PatientMapper {

  public Patient toDomain(PatientEntity e){
    if (e == null) return null;

    Patient d = new Patient();
    d.setPatientDocument(e.getPatientDocument());
    d.setFullName(e.getFullName());
    d.setBirthDate(e.getBirthDate());
    d.setGender(e.getGender());
    d.setAddress(e.getAddress());
    d.setPhoneNumber(e.getPhoneNumber());
    d.setEmail(e.getEmail());
    d.setEmergencyFirstName(e.getEmergencyFirstName());
    d.setEmergencyLastName(e.getEmergencyLastName());
    d.setRelationShip(e.getRelationShip());

    if (e.getEmergencyPhone() != null && !e.getEmergencyPhone().isBlank()) {
      try { d.setEmergencyPhone(Integer.parseInt(e.getEmergencyPhone())); }
      catch (NumberFormatException ex) { d.setEmergencyPhone(0); }
    }

    boolean hasPolicy = (e.getCompanyName() != null) || (e.getEndDatePolicy() != null) || (e.getNumberPolicy() != 0L);
    if (hasPolicy) {
      MedicalInsurance mi = new MedicalInsurance();
      mi.setCompanyName(e.getCompanyName());
      mi.setNumberPolicy(e.getNumberPolicy());
      mi.setStatusPolicy(e.isStatusPolicy());
      mi.setValidityDaysPolicy(e.getValidityDaysPolicy());
      mi.setEndDatePolicy(e.getEndDatePolicy());
      d.setInsurancePolicy(mi);
    } else {
      d.setInsurancePolicy(null);
    }

    return d;
  }

  public PatientEntity toEntity(Patient d){
    if (d == null) return null;

    PatientEntity e = new PatientEntity();
    e.setPatientDocument(d.getPatientDocument());
    e.setFullName(d.getFullName());
    e.setBirthDate(d.getBirthDate());
    e.setGender(d.getGender());
    e.setAddress(d.getAddress());
    e.setPhoneNumber(d.getPhoneNumber());
    e.setEmail(d.getEmail());
    e.setEmergencyFirstName(d.getEmergencyFirstName());
    e.setEmergencyLastName(d.getEmergencyLastName());
    e.setRelationShip(d.getRelationShip());
    e.setEmergencyPhone(String.valueOf(d.getEmergencyPhone()));

    MedicalInsurance mi = d.getInsurancePolicy();
    if (mi != null) {
      e.setCompanyName(mi.getCompanyName());
      e.setNumberPolicy(mi.getNumberPolicy());
      e.setStatusPolicy(mi.isStatusPolicy());
      e.setValidityDaysPolicy(mi.getValidityDaysPolicy());
      e.setEndDatePolicy(mi.getEndDatePolicy());
    } else {
      e.setCompanyName(null);
      e.setNumberPolicy(0L);
      e.setStatusPolicy(false);
      e.setValidityDaysPolicy(0);
      e.setEndDatePolicy(null);
    }

    return e;
  }
}
