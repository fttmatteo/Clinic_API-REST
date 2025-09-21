package app.domain.services;

import java.util.Calendar;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Invoice;
import app.domain.model.MedicalInsurance;
import app.domain.model.Patient;
import app.domain.ports.InvoicePort;
import app.domain.ports.PatientPort;
import app.application.exceptions.BusinessException;

@Service
public class GenerateInvoice {

    @Autowired private PatientPort patientPort;
    @Autowired private InvoicePort invoicePort;

    public Invoice generate(Invoice invoice, long totalService, Date invoiceDate) throws Exception {
        Patient patient = patientPort.findByPatient(invoice.getPatientDocument());
        if (patient == null) throw new BusinessException("el paciente no existe");

        invoice.setPatientName(patient.getFullName());
        invoice.setPatientAge(calcAge(patient.getBirthDate()));

        MedicalInsurance medicalInsurance = patient.getInsurancePolicy();
        boolean active = (medicalInsurance != null && medicalInsurance.isStatusPolicy());
        if (active) {
            invoice.setCompanyName(medicalInsurance.getCompanyName());
            invoice.setNumberPolicy(medicalInsurance.getNumberPolicy());
            invoice.setEndDatePolicy(medicalInsurance.getEndDatePolicy());
            invoice.setValidityDaysPolicy(daysBetween(invoiceDate, medicalInsurance.getEndDatePolicy()));
        }

        long copayment;
        long totalPatient;
        long totalInsurance;

        if (active) {
            copayment = 50_000L;
            totalPatient = copayment;
            totalInsurance = Math.max(0L, totalService - copayment);
        } else {
            copayment = totalService;
            totalPatient = totalService;
            totalInsurance = 0L;
        }

        invoice.setCopayment(copayment);
        invoice.setTotalPatient(totalPatient);
        invoice.setTotalInsurance(totalInsurance);

        return invoicePort.save(invoice);
    }

    private int calcAge(java.sql.Date birthDate) {
        if (birthDate == null) return 0;
        Calendar b = Calendar.getInstance(); b.setTime(birthDate);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - b.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < b.get(Calendar.DAY_OF_YEAR)) age--;
        return Math.max(age, 0);
    }

    private int daysBetween(java.sql.Date from, java.sql.Date to) {
        if (from == null || to == null) return 0;
        long ms = to.getTime() - from.getTime();
        return (int) Math.max(ms / (1000L*60*60*24), 0);
    }
}
