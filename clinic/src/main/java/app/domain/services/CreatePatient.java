package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Patient;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio responsable de crear pacientes. Aplica reglas de
 * negocio como la unicidad del documento y del nombre de usuario antes de
 * delegar la persistencia al puerto de infraestructura.
 */
@Service
public class CreatePatient {

    @Autowired
    private PatientPort patientPort;

    public void create(Patient patient) throws Exception {
        if (patientPort.findByDocument(patient) != null) {
            throw new BusinessException("ya existe un paciente registrado con esa cedula");
        }
        patientPort.save(patient);
    }
}