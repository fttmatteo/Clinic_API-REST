package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Medicine;
import app.domain.ports.MedicinePort;

/**
 * Servicio de dominio para consultar medicamentos en el inventario.  Permite
 * recuperar todos los medicamentos o uno por identificador a través del
 * puerto de infraestructura.
 */
@Service
public class SearchMedicine {
    @Autowired
    private MedicinePort medicinePort;

    public List<Medicine> findAll() throws Exception {
        return medicinePort.findAll();
    }

    public Medicine findById(String id) throws Exception {
        return medicinePort.findById(id);
    }
}