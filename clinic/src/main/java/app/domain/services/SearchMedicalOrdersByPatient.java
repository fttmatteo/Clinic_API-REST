package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.ports.MedicalOrderPort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio encargado de buscar las órdenes médicas de un
 * paciente. Valida la existencia del paciente antes de consultar las órdenes.
 */
@Service
public class SearchMedicalOrdersByPatient {

    @Autowired
    private PatientPort patientPort;
    @Autowired
    private MedicalOrderPort orderPort;

    public List<MedicalOrder> search(Patient patient) throws Exception {
        Patient existing = patientPort.findByDocument(patient);
        if (existing == null) {
            throw new Exception("No existe el paciente buscado");
        }
        patient.setId(existing.getId());
        patient.setFullName(existing.getFullName());
        return orderPort.findByPatient(patient);
    }
}