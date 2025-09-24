// RecordVitalSigns.java
package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.VitalSigns;
import app.domain.ports.VitalSignsPort;

@Service
public class RecordVitalSigns {
	
	@Autowired
	private VitalSignsPort vitalSignsPort;

	public VitalSigns execute(VitalSigns vitalSigns) throws Exception {
		if (vitalSigns == null) throw new Exception("Los signos vitales son obligatorios");
		if (vitalSigns.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
		if (vitalSigns.getRegistrationDate() == null) throw new Exception("La fecha de registro es obligatoria");
		return vitalSignsPort.save(vitalSigns);
	}
}
