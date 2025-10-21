package app.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.services.CreateInvoice;
import app.domain.services.CreatePatient;
import app.domain.services.SearchMedicalOrdersByPatient;

/**
 * Caso de uso para las funciones del personal administrativo. Permite
 * registrar pacientes, emitir facturas y consultar las órdenes médicas de
 * un paciente.
 */
@Service
public class AdministrativeUseCase {

    @Autowired
    private CreatePatient createPatient;
    @Autowired
    private CreateInvoice createInvoice;
    @Autowired
    private SearchMedicalOrdersByPatient searchOrders;

    public void createPatient(Patient patient) throws Exception {
        createPatient.create(patient);
    }

    public void createInvoice(Invoice invoice) throws Exception {
        createInvoice.create(invoice);
    }

    public List<MedicalOrder> searchMedicalOrders(Patient patient) throws Exception {
        return searchOrders.search(patient);
    }
}