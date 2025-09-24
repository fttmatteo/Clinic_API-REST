package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.ports.PatientPort;

@Service
public class CreatePatient {
	
	@Autowired
	private PatientPort patientPort;

	public Patient execute(Patient patient) throws Exception {
		if (patient == null) throw new Exception("El paciente es obligatorio");
		if (patient.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
		if (patientPort.findByPatient(patient.getPatientDocument()) != null) {
			throw new Exception("Ya existe un paciente con esa cédula");
		}
		return patientPort.save(patient);
	}
}
