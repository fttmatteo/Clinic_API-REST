package app.domain.model;

import java.sql.Date;

/**
 * Representa la póliza de seguro médico de un paciente. Contiene el nombre
 * de la compañía de seguros, el número de la póliza, el estado de la póliza
 * (activa o inactiva) y la fecha de finalización de la misma. Cuando la
 * póliza está activa, se calculará un copago fijo; si no está activa, el
 * paciente deberá asumir la totalidad del costo de los servicios.
 */
public class InsurancePolicy {
    /**
     * Identificador interno de la póliza. Este identificador se utiliza para
     * mapear la entidad con la base de datos. Un valor de cero indica que
     * todavía no ha sido persistida.
     */
    private Long id;
    private String companyName;
    private String policyNumber;
    private boolean active;
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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