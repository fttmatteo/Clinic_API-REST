package app.domain.ports;

import app.domain.model.Invoice;
import app.domain.model.Patient;

/**
 * Puerto de persistencia para las facturas. Permite almacenar facturas
 * emitidas por la clínica.
 */
public interface InvoicePort {
    void save(Invoice invoice) throws Exception;
    double sumCopayByPatientAndYear(Patient patient, int year) throws Exception;
}