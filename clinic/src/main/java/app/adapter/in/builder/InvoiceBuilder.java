package app.adapter.in.builder;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.InvoiceValidator;
import app.domain.model.Employee;
import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;

/**
 * Builder para {@link Invoice}. Crea una factura a partir de los datos
 * recibidos y realiza las conversiones necesarias. La validación de los
 * datos se delega a {@link InvoiceValidator}.
 */
@Component
public class InvoiceBuilder {
    @Autowired
    private InvoiceValidator invoiceValidator;
    public Invoice build(String patientId, String doctorDocument, String productName,
                         String productAmount, String isMedicine, String orderId) throws Exception {
        Invoice invoice = new Invoice();
        Patient patient = new Patient();
        patient.setId(invoiceValidator.patientIdValidator(patientId));
        invoice.setPatient(patient);
        if (doctorDocument != null && !doctorDocument.trim().isEmpty()) {
            Employee doctor = new Employee();
            doctor.setDocument(invoiceValidator.doctorDocumentValidator(doctorDocument));
            invoice.setDoctor(doctor);
        }
        invoice.setProductName(invoiceValidator.productNameValidator(productName));
        invoice.setProductAmount(invoiceValidator.amountValidator(productAmount));
        boolean medicine = invoiceValidator.isMedicineValidator(isMedicine);
        invoice.setMedicine(medicine);
        if (medicine) {
            MedicalOrder order = new MedicalOrder();
            order.setId(invoiceValidator.orderIdValidator(orderId));
            invoice.setOrder(order);
        }
        invoice.setDate(new Date(System.currentTimeMillis()));
        return invoice;
    }
}