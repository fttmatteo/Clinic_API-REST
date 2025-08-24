package app.domain.model;

import java.sql.Date;

public class MedicalInsurance {
    private String companyName;
    private long numberPolicy;
    private boolean statusPolicy;
    private int validityDaysPolicy;
    private Date endDatePolicy;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getNumberPolicy() {
        return numberPolicy;
    }

    public void setNumberPolicy(long numberPolicy) {
        this.numberPolicy = numberPolicy;
    }

    public boolean isStatusPolicy() {
        return statusPolicy;
    }

    public void setStatusPolicy(boolean statusPolicy) {
        this.statusPolicy = statusPolicy;
    }

    public int getValidityDaysPolicy() {
        return validityDaysPolicy;
    }

    public void setValidityDaysPolicy(int validityDaysPolicy) {
        this.validityDaysPolicy = validityDaysPolicy;
    }

    public Date getEndDatePolicy() {
        return endDatePolicy;
    }

    public void setEndDatePolicy(Date endDatePolicy) {
        this.endDatePolicy = endDatePolicy;
    }
}
