package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="vital_signs")
public class VitalSignsEntity {

  @EmbeddedId
  private VitalSignsKey vitalId;

  @Column(length=7) private String bloodPressure; // NN/NN
  private float temperature;
  private int pulse;
  private int oxygenSaturation;

  public VitalSignsKey getVitalId() { return vitalId; }
  public void setVitalId(VitalSignsKey vitalId) { this.vitalId = vitalId; }
  public String getBloodPressure() { return bloodPressure; }
  public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
  public float getTemperature() { return temperature; }
  public void setTemperature(float temperature) { this.temperature = temperature; }
  public int getPulse() { return pulse; }
  public void setPulse(int pulse) { this.pulse = pulse; }
  public int getOxygenSaturation() { return oxygenSaturation; }
  public void setOxygenSaturation(int oxygenSaturation) { this.oxygenSaturation = oxygenSaturation; }
}
