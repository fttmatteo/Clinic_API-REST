package app.domain.ports;

import app.domain.model.Patient;

public interface PatientPort {
	public Patient findByDocument(Patient document) throws Exception;
	public void save(Patient patient) throws Exception;
}