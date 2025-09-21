package app.domain.model;

import java.sql.Date;

public class Invoice {
    private long id;
    private String patientName;
    private int patientAge;
    private int patientDocument;
    private String doctorName;
    private String companyName;
    private long numberPolicy;
    private int validityDaysPolicy;
    private Date endDatePolicy;
    private String serviceDescription;
    private long copayment;
    private long totalPatient;
    private long totalInsurance;    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(int patientDocument) {
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

    public long getCopayment() {
        return copayment;
    }

    public void setCopayment(long copayment) {
        this.copayment = copayment;
    }

    public long getTotalPatient() {
        return totalPatient;
    }

    public void setTotalPatient(long totalPatient) {
        this.totalPatient = totalPatient;
    }

    public long getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(long totalInsurance) {
        this.totalInsurance = totalInsurance;
    }
    }
