package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

@Service
public class CreateInvoice {

    @Autowired
    private InvoicePort invoicePort;

    @Autowired
    private BillingService billingService;

    /**
     * Crea la factura aplicando reglas de copago/aseguradora antes de persistir.
     * @param invoice     factura con datos básicos
     * @param totalAmount valor bruto del servicio
     * @param ytdCopay    copago acumulado anual del paciente (puedes pasar 0D de inicio)
     */
    public Invoice execute(Invoice invoice, Double totalAmount, Double ytdCopay) throws Exception {
        if (invoice == null) throw new Exception("La factura es obligatoria");
        if (invoice.getInvoiceId() == null) throw new Exception("El id de factura es obligatorio");
        if (invoice.getInvoiceDate() == null) throw new Exception("La fecha de la factura es obligatoria");
        if (invoice.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
        if (invoice.getServiceDescription() == null || invoice.getServiceDescription().isBlank())
            throw new Exception("La descripción del servicio es obligatoria");

        billingService.applyCopayAndTotals(invoice, totalAmount, ytdCopay);
        return invoicePort.save(invoice);
    }
}
