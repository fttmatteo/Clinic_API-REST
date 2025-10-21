package app.domain.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Employee;
import app.domain.model.Invoice;
import app.domain.model.InsurancePolicy;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
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
        Patient patient = patientPort.findById(invoice.getPatient());
        if (patient == null) {
            throw new BusinessException("La factura debe tener un paciente asociado");
        }
        Employee doctor = null;
        if (invoice.getDoctor() != null) {
            doctor = employeePort.findByDocument(invoice.getDoctor());
            if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
                throw new BusinessException("La factura debe tener un médico válido si aplica");
            }
            invoice.setDoctor(doctor);
        }
        if (invoice.isMedicine()) {
            MedicalOrder order = invoice.getOrder();
            if (order == null) {
                throw new BusinessException("La venta de un medicamento requiere de una orden asociada");
            }
            order = orderPort.findById(order);
            if (order == null || order.getPatient().getId() != patient.getId()) {
                throw new BusinessException("La venta de un medicamento requiere de una orden válida del paciente");
            }
            invoice.setOrder(order);
        }
        invoice.setPatient(patient);
        invoice.setDate(new Date(System.currentTimeMillis()));

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