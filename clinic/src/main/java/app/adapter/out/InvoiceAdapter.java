package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;
import app.infrastructure.persistence.repository.InvoiceRepository;
import app.infrastructure.persistence.mapper.InvoiceMapper;

@Service
public class InvoiceAdapter implements InvoicePort {

    private final InvoiceRepository repository;
    private final InvoiceMapper mapper;

    public InvoiceAdapter(InvoiceRepository repository, InvoiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Invoice findByInvoiceId(long invoiceId) throws Exception {
        return repository.findById(invoiceId).map(mapper::toDomain).orElse(null);
    }

    @Override
    public Invoice save(Invoice invoice) throws Exception {
        return mapper.toDomain(repository.save(mapper.toEntity(invoice)));
    }
}
