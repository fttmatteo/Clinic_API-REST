package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;
import app.infrastructure.persistence.entities.InvoiceEntity;
import app.infrastructure.persistence.mapper.InvoiceMapper;
import app.infrastructure.persistence.repository.InvoiceRepository;

/**
 * Adaptador de infraestructura que implementa el puerto {@link InvoicePort}.
 * Se encarga de convertir entre el modelo de dominio y la entidad
 * persistente y delegar la operación de guardado al repositorio JPA.
 */
@Service
public class InvoiceAdapter implements InvoicePort {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void save(Invoice invoice) throws Exception {
        InvoiceEntity entity = InvoiceMapper.toEntity(invoice);
        invoiceRepository.save(entity);
        invoice.setId(entity.getId());
    }
}