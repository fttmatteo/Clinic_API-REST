package app.domain.ports;

import app.domain.model.Invoice;

public interface InvoicePort {
    Invoice findByInvoiceId(Long invoiceId) throws Exception;
    Invoice save(Invoice invoice) throws Exception;
}
