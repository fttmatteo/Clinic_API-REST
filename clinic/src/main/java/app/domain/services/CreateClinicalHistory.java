package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.ClinicalHistory;
import app.domain.ports.ClinicalHistoryPort;

@Service
public class CreateClinicalHistory {
	
	@Autowired
	private ClinicalHistoryPort clinicalHistoryPort;

	public ClinicalHistory execute(ClinicalHistory history) throws Exception {
		if (history == null) throw new Exception("La historia clínica es obligatoria");
		if (history.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
		if (history.getDoctorDocument() == null) throw new Exception("La cédula del médico es obligatoria");
		if (history.getAttentionDate() == null) throw new Exception("La fecha de atención es obligatoria");
		return clinicalHistoryPort.save(history);
	}
}
