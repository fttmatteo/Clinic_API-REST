package app.domain.services;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;

public class FindMedicine {
    private MedicinePort medicinePort;

    public FindMedicine(MedicinePort medicinePort) {
        this.medicinePort = medicinePort;
    }

    public Medicine findById(Medicine medicine) throws Exception {
        Medicine found = medicinePort.findById(medicine);
        if (found == null) {
            throw new Exception("No existe un medicamento registrado con ese ID");
        }
        return found;
    }
}
