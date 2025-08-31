package app.domain.services;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

public class InvoiceService {
    private InvoicePort invoicePort;

    public InvoiceService(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    public void saveInvoice(Invoice invoice) throws Exception {
        invoicePort.save(invoice);
    }

    public Invoice findInvoiceById(Long id) throws Exception {
        return invoicePort.findById(id);
    }

}
