package app.domain.ports;

import app.domain.model.Patient;
import java.util.List;

public interface PatientPort {
	public Patient findByDocument(Patient patient) throws Exception;
	public List<Patient> searchByName(Patient patient) throws Exception;
	public void save(Patient patient) throws Exception;
	public void update(Patient patient) throws Exception;
	public void delete(Patient patient) throws Exception;
}


