package app.domain.services;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

public class CreateInvoice {
    private InvoicePort invoicePort;

    public void create(Invoice invoice) throws Exception {
        if (invoice == null) {
            throw new Exception("La factura es nulo");
        }
        invoicePort.save(invoice);
    }

    public Invoice findById(Long id) throws Exception {
        Invoice found = invoicePort.findById(id);
        if (found == null) {
            throw new Exception("No existe una factura registrada con ese ID");
        }
        return found;
    }
}
