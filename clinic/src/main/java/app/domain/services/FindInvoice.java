package app.domain.services;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

public class FindInvoice {
    private InvoicePort invoicePort;

    public FindInvoice(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    public Invoice findById(Long id) throws Exception {
        Invoice found = invoicePort.findById(id);
        if (found == null) {
            throw new Exception("No existe una factura registrada con ese ID");
        }
        return found;
    }
}
