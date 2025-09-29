package app.domain.ports;

import java.util.List;

import app.domain.model.inventory.Medicine;

/**
 * Puerto de persistencia para operaciones relacionadas con los
 * medicamentos. Permite almacenar, consultar y listar medicamentos sin
 * acoplarse a la tecnología de persistencia. Los identificadores de
 * medicamentos son cadenas alfanuméricas definidas en el catálogo.
 */
public interface MedicinePort {
    void save(Medicine medicine) throws Exception;
    Medicine findById(String id) throws Exception;
    List<Medicine> findAll() throws Exception;
}