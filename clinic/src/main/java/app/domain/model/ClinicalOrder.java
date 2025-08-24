package app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
    private int id;
    private Patient fullName;
    private User patient;
    private User medic;
    private String medicine;
    private String doce;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getFullName() {
        return fullName;
    }

    public void setFullName(Patient fullName) {
        this.fullName = fullName;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getMedic() {
        return medic;
    }

    public void setMedic(User medic) {
        this.medic = medic;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDoce() {
        return doce;
    }

    public void setDoce(String doce) {
        this.doce = doce;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
