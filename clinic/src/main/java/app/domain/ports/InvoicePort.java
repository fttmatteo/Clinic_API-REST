package app.domain.ports;

import app.domain.model.Invoice;

public interface InvoicePort {
    public Invoice findById(Long id) throws Exception;
    public void save(Invoice invoice) throws Exception;
}