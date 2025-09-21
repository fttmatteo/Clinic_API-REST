package app.adapter.in.rest.request;

public class InsuranceRequest {
    private String companyName;
    private String numberPolicy;
    private String statusPolicy;   // true/false
    private String endDatePolicy;  // yyyy-MM-dd

    public InsuranceRequest() {}

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getNumberPolicy() { return numberPolicy; }
    public void setNumberPolicy(String numberPolicy) { this.numberPolicy = numberPolicy; }
    public String getStatusPolicy() { return statusPolicy; }
    public void setStatusPolicy(String statusPolicy) { this.statusPolicy = statusPolicy; }
    public String getEndDatePolicy() { return endDatePolicy; }
    public void setEndDatePolicy(String endDatePolicy) { this.endDatePolicy = endDatePolicy; }
}
