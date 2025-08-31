package app.domain.ports;

import app.domain.model.Medicine;

public interface MedicinePort {
    Medicine findById(Medicine medicine) throws Exception;
    void save(Medicine medicine) throws Exception;
}
