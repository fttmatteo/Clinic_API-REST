package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class VitalSignsKey implements Serializable {
  private int patientDocument;
  private Date registrationDate;

  public VitalSignsKey() {}
  public VitalSignsKey(int patientDocument, Date registrationDate){ this.patientDocument=patientDocument; this.registrationDate=registrationDate; }

  public int getPatientDocument(){ return patientDocument; }
  public void setPatientDocument(int patientDocument){ this.patientDocument = patientDocument; }
  public Date getRegistrationDate(){ return registrationDate; }
  public void setRegistrationDate(Date registrationDate){ this.registrationDate = registrationDate; }

  @Override public boolean equals(Object o){
    if(this==o) return true;
    if(!(o instanceof VitalSignsKey)) return false;
    VitalSignsKey k=(VitalSignsKey)o;
    return patientDocument==k.patientDocument && java.util.Objects.equals(registrationDate,k.registrationDate);
  }
  @Override public int hashCode(){ return Objects.hash(patientDocument,registrationDate); }
}
