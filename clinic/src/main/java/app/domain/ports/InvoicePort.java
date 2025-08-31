package app.domain.ports;

import app.domain.model.Invoice;

public interface InvoicePort {
    public Invoice findById(Invoice invoice) throws Exception;
    public void save(Invoice invoice) throws Exception;
}