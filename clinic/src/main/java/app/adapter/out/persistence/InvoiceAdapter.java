package app.adapter.out.persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.model.Patient;
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

    @Override
    public double sumCopayByPatientAndYear(Patient patient, int year) throws Exception {
        if (patient == null) return 0;
        Long patientId = patient.getId();
        if (patientId == null) return 0;
        return invoiceRepository.sumCopayByPatientIdAndYear(patientId, year);
    }

    @Override
    public List<Invoice> findByPatient(Patient patient) throws Exception {
        if (patient == null || patient.getId() == null) {
            return Collections.emptyList();
        }
        List<InvoiceEntity> entities = invoiceRepository.findDetailedByPatientId(patient.getId());
        return entities.stream()
                .map(InvoiceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
