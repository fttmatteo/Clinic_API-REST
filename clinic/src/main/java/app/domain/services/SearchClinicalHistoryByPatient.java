// SearchClinicalHistoryByPatient.java
package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;

@Service
public class SearchClinicalHistoryByPatient {
	
	@Autowired
	private ClinicalHistoryPort clinicalHistoryPort;

	public List<ClinicalHistory> execute(Integer patientDocument) throws Exception {
		if (patientDocument == null) throw new Exception("La cédula del paciente es obligatoria");
		return clinicalHistoryPort.findByPatient(patientDocument);
	}
}
