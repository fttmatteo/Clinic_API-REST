package app.domain.model;

public class Patient extends Person {
    private String gender;
    private String emergencyFirstName;
    private String emergencyLastName;
    private String relationShip;
    private int emergencyPhone;
    private MedicalInsurance insurancePolicy;

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmergencyFirstName() {
        return emergencyFirstName;
    }
    public void setEmergencyFirstName(String emergencyFirstName) {
        this.emergencyFirstName = emergencyFirstName;
    }
    public String getEmergencyLastName() {
        return emergencyLastName;
    }
    public void setEmergencyLastName(String emergencyLastName) {
        this.emergencyLastName = emergencyLastName;
    }
    public String getRelationShip() {
        return relationShip;
    }
    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }
    public int getEmergencyPhone() {
        return emergencyPhone;
    }
    public void setEmergencyPhone(int emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
    public MedicalInsurance getInsurancePolicy() {
        return insurancePolicy;
    }
    public void setInsurancePolicy(MedicalInsurance insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}