package app.adapter.in.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.PatientValidator;
import app.adapter.in.validators.EmployeeValidator;
import app.domain.model.Employee;
import app.domain.model.MedicalOrder;
import app.domain.model.OrderItem;
import app.domain.model.Patient;

/**
 * Builder para {@link MedicalOrder}. Crea una orden médica a partir de
 * identificadores y una lista de ítems previamente validados. Este builder
 * también se encarga de instanciar los objetos de paciente y médico con
 * solo sus identificadores para que luego sean resueltos por el servicio.
 */
@Component
public class MedicalOrderBuilder {
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private PatientValidator patientValidator;

    public MedicalOrder build(String doctorDocument, String patientId, List<OrderItem> items) throws Exception {
        MedicalOrder order = new MedicalOrder();
        Employee doctor = new Employee();
        doctor.setDocument(employeeValidator.documentValidator(doctorDocument));
        order.setDoctor(doctor);
        Patient patient = new Patient();
        patient.setDocument(patientValidator.documentValidator(patientId));
        order.setPatient(patient);
        order.setItems(items);
        return order;
    }
}