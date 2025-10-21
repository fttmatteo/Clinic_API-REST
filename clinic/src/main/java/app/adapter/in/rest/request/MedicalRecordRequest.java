package app.adapter.in.rest.request;

/**
 * Solicitud para registrar una historia clínica. Contiene los datos
 * necesarios como el médico que atiende, el paciente, una orden
 * opcional y la información clínica (motivo, síntomas y diagnóstico).
 */
public class MedicalRecordRequest {
    private String doctorDocument;
    private String patientId;
    private String orderId;
    private String motive;
    private String symptoms;
    private String diagnosis;

    public String getDoctorDocument() {
        return doctorDocument;
    }
    public void setDoctorDocument(String doctorDocument) {
        this.doctorDocument = doctorDocument;
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getMotive() {
        return motive;
    }
    public void setMotive(String motive) {
        this.motive = motive;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}