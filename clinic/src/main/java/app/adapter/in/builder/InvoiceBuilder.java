package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.InvoiceValidator;
import app.domain.model.Invoice;

@Component
public class InvoiceBuilder {

    @Autowired private InvoiceValidator validator;

    public Invoice build(
        String patientDocument,
        String doctorName,
        String serviceDescription
    ) throws Exception {

        Invoice invoice = new Invoice();
        invoice.setPatientDocument(validator.patientDocumentValidator(patientDocument));
        invoice.setDoctorName(validator.maxLength("nombre del profesional", doctorName, 120));
        invoice.setServiceDescription(validator.maxLength("detalle clínico", serviceDescription, 1000));
        return invoice;
    }
}
