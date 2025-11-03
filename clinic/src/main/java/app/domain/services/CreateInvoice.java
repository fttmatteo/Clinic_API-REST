package app.domain.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.Invoice;
import app.domain.model.InsurancePolicy;
import app.domain.model.MedicalOrder;
import app.domain.model.OrderItem;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.model.enums.OrderItemType;
import app.domain.ports.EmployeePort;
import app.domain.ports.InvoicePort;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio para la creación de facturas. Aplica reglas como la
 * obligatoriedad de asociar una orden médica cuando se factura un
 * medicamento y la verificación de la existencia del paciente y del médico.
 */
@Service
public class CreateInvoice {

    @Autowired
    private PatientPort patientPort;
    @Autowired
    private EmployeePort employeePort;
    @Autowired
    private MedicalOrderPort orderPort;
    @Autowired
    private InvoicePort invoicePort;

    public void create(Invoice invoice) throws Exception {
        if (invoice.getPatient() == null || invoice.getPatient().getDocument() == null) {
            throw new BusinessException("La factura debe indicar el documento del paciente");
        }
        Patient patient = patientPort.findByDocument(invoice.getPatient());
        if (patient == null) {
            throw new BusinessException("La factura debe tener un paciente asociado");
        }
        MedicalOrder orderCriteria = invoice.getOrder();
        if (orderCriteria == null || orderCriteria.getOrderNumber() == null || orderCriteria.getOrderNumber().trim().isEmpty()) {
            throw new BusinessException("La factura debe tener una orden clínica asociada");
        }
        MedicalOrder order = orderPort.findByOrderNumber(orderCriteria.getOrderNumber());
        if (order == null) {
            throw new BusinessException("La factura debe tener una orden clínica válida");
        }
        if (order.getPatient() == null || order.getPatient().getId() == null
                || !order.getPatient().getId().equals(patient.getId())) {
            throw new BusinessException("La orden clínica no corresponde al paciente de la factura");
        }

        Employee doctor = order.getDoctor();
        if (doctor == null || doctor.getDocument() == null) {
            throw new BusinessException("La orden clínica asociada debe tener un médico tratante identificado");
        }
        doctor = employeePort.findByDocument(doctor);
        if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
            throw new BusinessException("La orden clínica asociada debe tener un médico tratante válido");
        }
        if (invoice.getDoctor() != null && invoice.getDoctor().getDocument() != null
                && !invoice.getDoctor().getDocument().equals(doctor.getDocument())) {
            throw new BusinessException("El médico indicado en la factura debe coincidir con el médico tratante de la orden");
        }
        order.setDoctor(doctor);
        order.setPatient(patient);
        invoice.setDoctor(doctor);
        invoice.setOrder(order);
        invoice.setPatient(patient);
        invoice.setDate(new Date(System.currentTimeMillis()));
        double totalAmount = 0;
        boolean hasMedicine = false;
        StringBuilder description = new StringBuilder();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                if (item.getName() != null && !item.getName().trim().isEmpty()) {
                    if (description.length() > 0) {
                        description.append(", ");
                    }
                    description.append(item.getName().trim());
                }
                if (item.getCost() != null) {
                    totalAmount += item.getCost();
                }
                if (item.getType() == OrderItemType.MEDICINE) {
                    hasMedicine = true;
                }
            }
        }
        if (description.length() == 0) {
            description.append("Orden ").append(order.getOrderNumber());
        }
        invoice.setProductName(description.toString());
        invoice.setProductAmount(totalAmount);
        invoice.setMedicine(hasMedicine);

        double copay;
        double billedToInsurer;
        InsurancePolicy policy = patient.getInsurancePolicy();
        boolean hasActivePolicy = false;
        if (policy != null && policy.isActive()) {
            Date today = new Date(System.currentTimeMillis());
            if (policy.getExpiryDate() == null || !policy.getExpiryDate().before(today)) {
                hasActivePolicy = true;
            }
        }
        if (hasActivePolicy) {
            int currentYear = Integer.parseInt(new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()));
            double totalCopayYear = invoicePort.sumCopayByPatientAndYear(patient, currentYear);
            if (totalCopayYear >= 1000000) {
                copay = 0;
            } else {
                copay = 50000;
                if (totalCopayYear + copay > 1000000) {
                    copay = 0;
                }
            }
            billedToInsurer = invoice.getProductAmount() - copay;
            if (billedToInsurer < 0) {
                billedToInsurer = 0;
            }
        } else {
            copay = invoice.getProductAmount();
            billedToInsurer = 0;
        }
        invoice.setCopay(copay);
        invoice.setBilledToInsurer(billedToInsurer);
        invoicePort.save(invoice);
    }
}
