package app.domain.ports;

import java.util.List;
import app.domain.model.Medicine;

public interface MedicinePort {
    Medicine findById(Long id) throws Exception;
    List<Medicine> listAll() throws Exception;
    Medicine save(Medicine medicine) throws Exception;
    Medicine update(Medicine medicine) throws Exception;
    void delete(Long id) throws Exception;
}
