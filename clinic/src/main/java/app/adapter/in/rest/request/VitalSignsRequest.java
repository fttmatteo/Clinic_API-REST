package app.adapter.in.rest.request;

public class VitalSignsRequest {
    private String patientDocument;
    private String date;               // yyyy-MM-dd
    private String bloodPressure;      // NN/NN
    private String temperature;        // float
    private String pulse;              // int
    private String oxygenSaturation;   // int 0..100

    public VitalSignsRequest() {}

    public String getPatientDocument() { return patientDocument; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public String getTemperature() { return temperature; }
    public void setTemperature(String temperature) { this.temperature = temperature; }
    public String getPulse() { return pulse; }
    public void setPulse(String pulse) { this.pulse = pulse; }
    public String getOxygenSaturation() { return oxygenSaturation; }
    public void setOxygenSaturation(String oxygenSaturation) { this.oxygenSaturation = oxygenSaturation; }
}
