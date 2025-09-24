package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

@Service
public class CreateInvoice {
	
	@Autowired
	private InvoicePort invoicePort;

	public Invoice execute(Invoice invoice) throws Exception {
		if (invoice == null) throw new Exception("La factura es obligatoria");
		if (invoice.getInvoiceId() == null) throw new Exception("El id de factura es obligatorio");
		if (invoice.getInvoiceDate() == null) throw new Exception("La fecha de la factura es obligatoria");
		if (invoice.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
		if (invoice.getServiceDescription() == null || invoice.getServiceDescription().isBlank()) {
			throw new Exception("La descripción del servicio es obligatoria");
		}
		return invoicePort.save(invoice);
	}
}
