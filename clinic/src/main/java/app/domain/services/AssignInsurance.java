// AssignInsurance.java
package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalInsurance;
import app.domain.model.Patient;
import app.domain.ports.PatientPort;

@Service
public class AssignInsurance {
	
	@Autowired
	private PatientPort patientPort;

	public Patient execute(Integer patientDocument, MedicalInsurance insurance) throws Exception {
		if (patientDocument == null) throw new Exception("La cédula del paciente es obligatoria");
		Patient p = patientPort.findByPatient(patientDocument);
		if (p == null) throw new Exception("No existe un paciente con esa cédula");
		if (insurance == null || insurance.getNumberPolicy() == null) {
			throw new Exception("La póliza es obligatoria");
		}
		p.setInsurancePolicy(insurance);
		return patientPort.save(p);
	}
}
