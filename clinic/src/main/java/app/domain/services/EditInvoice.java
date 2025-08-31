package app.domain.services;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

public class EditInvoice {
    private InvoicePort invoicePort;

    public EditInvoice(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    public void edit(Invoice invoice) throws Exception {
        if (invoice == null) {
            throw new Exception("La factura es nulo");
        }
        invoicePort.save(invoice);
    }
}
