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
}
