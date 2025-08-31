package app.domain.services;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;

public class CreateMedicine {
    private MedicinePort medicinePort;

    public void create(Medicine medicine) throws Exception {
        if (medicine == null) {
            throw new Exception("El medicamento es nulo");
        }
        medicinePort.save(medicine);
    }

    public Medicine findById(Long id) throws Exception {
        Medicine found = medicinePort.findById(id);
        if (found == null) {
            throw new Exception("No existe un medicamento registrado con ese ID");
        }
        return found;
    }
}
