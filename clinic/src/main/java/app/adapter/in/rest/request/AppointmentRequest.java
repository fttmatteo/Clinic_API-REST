package app.adapter.in.rest.request;

/**
 * Representa la estructura de la solicitud para crear una cita. Contiene
 * referencias al paciente y al médico mediante sus identificadores y la
 * fecha y hora programada en formato ISO 8601.
 */
public class AppointmentRequest {
    private String patientDocument;
    private String doctorDocument;
    private String dateTime;

    public String getPatientDocument() {
        return patientDocument;
    }
    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
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
