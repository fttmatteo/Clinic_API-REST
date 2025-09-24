package app.domain.model;

import java.sql.Date;

public class Invoice {
    private Long invoiceId;
    private Date invoiceDate;
    private String patientName;
    private Integer patientAge;
    private Integer patientDocument;
    private String doctorName;
    private String companyName;
    private Long numberPolicy;
    private Integer validityDaysPolicy;
    private Date endDatePolicy;
    private String serviceDescription;
    private String orderReference;
    private Double copayment;
    private Double totalPatient;
    private Double totalInsurance;    

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public Integer getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(Integer patientDocument) {
        this.patientDocument = patientDocument;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

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

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public Double getCopayment() {
        return copayment;
    }

    public void setCopayment(Double copayment) {
        this.copayment = copayment;
    }

    public Double getTotalPatient() {
        return totalPatient;
    }

    public void setTotalPatient(Double totalPatient) {
        this.totalPatient = totalPatient;
    }

    public Double getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(Double totalInsurance) {
        this.totalInsurance = totalInsurance;
    }
    }
