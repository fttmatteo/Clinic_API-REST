package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.PatientValidator;
import app.adapter.in.validators.VitalSignsValidator;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.VitalSignsRecord;

/**
 * Builder para {@link VitalSignsRecord}. Construye un registro de signos
 * vitales a partir de cadenas de texto y delega la validación a
 * {@link VitalSignsValidator}.
 */
@Component
public class VitalSignsBuilder {
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private PatientValidator patientValidator;
    @Autowired
    private VitalSignsValidator vitalSignsValidator;

    public VitalSignsRecord build(String nurseDocument, String patientId, String bloodPressure,
                                  String temperature, String pulse, String oxygenLevel) throws Exception {
        VitalSignsRecord record = new VitalSignsRecord();
        Employee nurse = new Employee();
        nurse.setDocument(employeeValidator.documentValidator(nurseDocument));
        record.setNurse(nurse);
        Patient patient = new Patient();
        patient.setDocument(patientValidator.documentValidator(patientId));
        record.setPatient(patient);
        record.setBloodPressure(vitalSignsValidator.bloodPressureValidator(bloodPressure));
        record.setTemperature(vitalSignsValidator.temperatureValidator(temperature));
        record.setPulse(vitalSignsValidator.pulseValidator(pulse));
        record.setOxygenLevel(vitalSignsValidator.oxygenLevelValidator(oxygenLevel));
        return record;
    }
}