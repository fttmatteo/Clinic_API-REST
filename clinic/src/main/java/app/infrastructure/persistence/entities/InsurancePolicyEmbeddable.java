package app.infrastructure.persistence.entities;

import java.sql.Date;

import jakarta.persistence.Embeddable;

/**
 * Representa la póliza de seguro asociada a un paciente. Se almacena
 * embebida dentro de la entidad de paciente y contiene información
 * básica como el nombre de la aseguradora, el número de póliza, el
 * estado de la misma y la fecha de vencimiento.
 */
@Embeddable
public class InsurancePolicyEmbeddable {
    private String companyName;
    private String policyNumber;
    private boolean active;
    private Date expiryDate;

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}