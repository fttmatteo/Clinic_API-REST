package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.inventory.Medicine;
import app.domain.ports.MedicinePort;

/**
 * Servicio de dominio responsable de crear medicamentos en el catálogo.
 * Aplica la regla de negocio de unicidad del identificador antes de
 * delegar la persistencia al puerto de infraestructura.
 */
@Service
public class CreateMedicine {

    @Autowired
    private MedicinePort medicinePort;

    public void create(Medicine medicine) throws Exception {
        if (medicinePort.findById(medicine.getId()) != null) {
            throw new BusinessException("ya existe un medicamento con ese identificador");
        }
        medicinePort.save(medicine);
    }
}