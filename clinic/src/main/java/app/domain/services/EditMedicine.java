package app.domain.services;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;

public class EditMedicine {
    private MedicinePort medicinePort;

    public EditMedicine(MedicinePort medicinePort) {
        this.medicinePort = medicinePort;
    }

    public void edit(Medicine medicine) throws Exception {
        if (medicine == null) {
            throw new Exception("El medicamento es nulo");
        }
        medicinePort.save(medicine);
    }
}
