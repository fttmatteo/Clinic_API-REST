package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.ClinicalOrder;
import app.domain.ports.ClinicalOrderPort;

@Service
public class SearchClinicalOrderByPatient {
	
	@Autowired
	private ClinicalOrderPort clinicalOrderPort;

	public List<ClinicalOrder> execute(Integer patientDocument) throws Exception {
		if (patientDocument == null) throw new Exception("La cédula del paciente es obligatoria");
		return clinicalOrderPort.findByPatient(patientDocument);
	}
}
