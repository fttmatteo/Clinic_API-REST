package app.application.usecase;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;

public class MedicineUseCase {
    private MedicinePort medicinePort;

    public MedicineUseCase(MedicinePort medicinePort) {
        this.medicinePort = medicinePort;
    }

    public void saveMedicine(Medicine medicine) throws Exception {
        medicinePort.save(medicine);
    }

    public Medicine findMedicineById(Long id) throws Exception {
        return medicinePort.findById(id);
    }
}
