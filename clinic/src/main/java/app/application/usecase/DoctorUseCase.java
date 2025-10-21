package app.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalOrder;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.services.CreateMedicalOrder;
import app.domain.services.CreateMedicalRecord;
import app.domain.services.SearchMedicalOrdersByPatient;

/**
 * Caso de uso para las funcionalidades de los médicos. Permite crear órdenes
 * y registros médicos y consultar las órdenes de un paciente.
 */
@Service
public class DoctorUseCase {

    @Autowired
    private CreateMedicalOrder createOrder;
    @Autowired
    private CreateMedicalRecord createRecord;
    @Autowired
    private SearchMedicalOrdersByPatient searchOrders;

    public void createMedicalOrder(MedicalOrder order) throws Exception {
        createOrder.create(order);
    }

    public void createMedicalRecord(MedicalRecord record) throws Exception {
        createRecord.create(record);
    }

    public List<MedicalOrder> searchMedicalOrders(Patient patient) throws Exception {
        return searchOrders.search(patient);
    }
}