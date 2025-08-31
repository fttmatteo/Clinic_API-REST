package app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
    private int id;
    private Patient fullName;
    private Employee patient;
    private Employee doctor;
    private String medicine;
    private int doce;
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

    public Employee getPatient() {
        return patient;
    }

    public void setPatient(Employee patient) {
        this.patient = patient;
    }

    public Employee getMedic() {
        return doctor;
    }

    public void setMedic(Employee medic) {
        this.doctor = medic;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getDoce() {
        return doce;
    }

    public void setDoce(int doce) {
        this.doce = doce;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
