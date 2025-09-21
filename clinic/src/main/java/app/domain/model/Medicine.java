package app.domain.model;

public class Medicine {
   private int medicineId;
   private String medicineName;
   private long price;

public int getMedicineId() {
    return medicineId;
}

public void setMedicineId(int medicineId) {
    this.medicineId = medicineId;
}

public String getMedicineName() {
    return medicineName;
}

public void setMedicineName(String medicineName) {
    this.medicineName = medicineName;
}

public long getPrice() {
    return price;
}

public void setPrice(long price) {
    this.price = price;
}
}
