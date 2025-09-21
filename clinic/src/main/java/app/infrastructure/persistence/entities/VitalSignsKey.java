package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class VitalSignsKey implements Serializable {
  private int patientDocument;
  private Date date;

  public VitalSignsKey() {}
  public VitalSignsKey(int patientDocument, Date date){ this.patientDocument=patientDocument; this.date=date; }

  public int getPatientDocument(){ return patientDocument; }
  public void setPatientDocument(int patientDocument){ this.patientDocument = patientDocument; }
  public Date getDate(){ return date; }
  public void setDate(Date date){ this.date = date; }

  @Override public boolean equals(Object o){
    if(this==o) return true;
    if(!(o instanceof VitalSignsKey)) return false;
    VitalSignsKey k=(VitalSignsKey)o;
    return patientDocument==k.patientDocument && java.util.Objects.equals(date,k.date);
  }
  @Override public int hashCode(){ return Objects.hash(patientDocument,date); }
}
