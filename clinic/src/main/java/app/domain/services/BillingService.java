package app.domain.services;

import java.sql.Date;
import org.springframework.stereotype.Service;
import app.domain.model.Invoice;

@Service
public class BillingService {

    private static final double COPAY_VALUE = 50_000D;
    private static final double ANNUAL_COPAY_CAP = 1_000_000D;

    /**
     * Aplica copago y totales en la factura según póliza activa/inactiva y tope anual.
     * @param invoice     factura con datos del paciente/seguro/fecha
     * @param totalAmount valor bruto del servicio (sumatoria de ítems/servicio)
     * @param ytdCopay    copago acumulado del paciente en el año (0D si no lo tienes aún)
     */
    public void applyCopayAndTotals(Invoice invoice, Double totalAmount, Double ytdCopay) throws Exception {
        if (invoice == null) throw new Exception("La factura es obligatoria");
        if (invoice.getInvoiceDate() == null) throw new Exception("La fecha de la factura es obligatoria");
        if (invoice.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
        if (totalAmount == null || totalAmount < 0) throw new Exception("El total del servicio es inválido");
        if (ytdCopay == null || ytdCopay < 0) ytdCopay = 0D;

        boolean policyActive = isPolicyActive(
            invoice.getCompanyName(),
            invoice.getNumberPolicy(),
            invoice.getValidityDaysPolicy(),
            invoice.getEndDatePolicy(),
            invoice.getInvoiceDate()
        );

        if (!policyActive) {
            invoice.setCopayment(0D);
            invoice.setTotalInsurance(0D);
            invoice.setTotalPatient(totalAmount);
            return;
        }

        double remainingCap = Math.max(0D, ANNUAL_COPAY_CAP - ytdCopay);
        double appliedCopay = Math.min(COPAY_VALUE, remainingCap);

        if (appliedCopay <= 0D) {
            invoice.setCopayment(0D);
            invoice.setTotalPatient(0D);
            invoice.setTotalInsurance(totalAmount);
            return;
        }

        double insurer = Math.max(0D, totalAmount - appliedCopay);
        invoice.setCopayment(appliedCopay);
        invoice.setTotalPatient(appliedCopay);
        invoice.setTotalInsurance(insurer);
    }

    private boolean isPolicyActive(String companyName, Long numberPolicy, Integer validityDays, Date endDate, Date invoiceDate) {
        if (companyName == null || companyName.isBlank()) return false;
        if (numberPolicy == null) return false;
        boolean byDays = (validityDays != null && validityDays > 0);
        boolean byEnd  = (endDate != null && !endDate.before(invoiceDate));
        return byDays || byEnd;
    }
}
