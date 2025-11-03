package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Invoice;
import app.domain.model.Patient;
import app.domain.ports.InvoicePort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio para consultar las facturas asociadas a un paciente.
 * Verifica la existencia del paciente y delega la b��squeda al puerto de
 * persistencia de facturas.
 */
@Service
public class SearchInvoices {

    @Autowired
    private PatientPort patientPort;
    @Autowired
    private InvoicePort invoicePort;

    public List<Invoice> byPatient(Patient patient) throws Exception {
        Patient existing = patientPort.findByDocument(patient);
        if (existing == null) {
            throw new BusinessException("No existe el paciente buscado");
        }
        patient.setId(existing.getId());
        patient.setFullName(existing.getFullName());
        return invoicePort.findByPatient(patient);
    }
}
