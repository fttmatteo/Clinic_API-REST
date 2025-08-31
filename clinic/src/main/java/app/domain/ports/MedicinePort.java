package app.domain.ports;

import app.domain.model.Medicine;

public interface MedicinePort {
    Medicine findById(Long id) throws Exception;
    void save(Medicine medicine) throws Exception;
}
