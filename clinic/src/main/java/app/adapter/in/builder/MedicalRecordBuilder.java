package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.MedicalRecordValidator;
import app.adapter.in.validators.MedicalOrderValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

/**
 * Builder para {@link MedicalRecord}. Construye la historia clínica
 * utilizando cadenas de texto para los identificadores y los campos
 * descriptivos. La validez de las referencias a paciente, médico y orden se
 * verifica en la capa de servicio.
 */
@Component
public class MedicalRecordBuilder {
    @Autowired
    private MedicalRecordValidator recordValidator;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private MedicalOrderValidator orderValidator;

    public MedicalRecord build(String doctorDocument, String patientId, String orderId,
                               String motive, String symptoms, String diagnosis) throws Exception {
        MedicalRecord record = new MedicalRecord();
        Employee doctor = new Employee();
        doctor.setDocument(employeeValidator.documentValidator(doctorDocument));
        record.setDoctor(doctor);
        Patient patient = new Patient();
        patient.setDocument(patientValidator.documentValidator(patientId));
        record.setPatient(patient);
        if (orderId != null && !orderId.trim().isEmpty()) {
            MedicalOrder order = new MedicalOrder();
            order.setId(orderValidator.idValidator(orderId));
            record.setMedicalOrder(order);
        }
        record.setMotive(recordValidator.motiveValidator(motive));
        record.setSymptoms(recordValidator.symptomsValidator(symptoms));
        record.setDiagnosis(recordValidator.diagnosisValidator(diagnosis));
        return record;
    }
}