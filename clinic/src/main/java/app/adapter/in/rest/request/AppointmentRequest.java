package app.adapter.in.rest.request;

/**
 * Representa la estructura de la solicitud para crear una cita. Contiene
 * referencias al paciente y al médico mediante sus identificadores y la
 * fecha y hora programada en formato ISO 8601.
 */
public class AppointmentRequest {
    private String patientId;
    private String doctorDocument;
    private String dateTime;

    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getDoctorDocument() {
        return doctorDocument;
    }
    public void setDoctorDocument(String doctorDocument) {
        this.doctorDocument = doctorDocument;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}