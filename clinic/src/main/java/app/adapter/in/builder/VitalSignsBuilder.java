package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.VitalSignsValidator;
import app.domain.model.VitalSigns;

@Component
public class VitalSignsBuilder {

    @Autowired private VitalSignsValidator validator;

    public VitalSigns build(
        String patientDocument,
        String registrationDate,
        String bloodPressure,
        String temperature,
        String pulse,
        String oxygenSaturation
    ) throws Exception {

        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setPatientDocument(validator.patientDocumentValidator(patientDocument));
        vitalSigns.setRegistrationDate(validator.dateValidatorField(registrationDate));
        vitalSigns.setBloodPressure(validator.bloodPressureValidatorField(bloodPressure));
        vitalSigns.setTemperature(validator.temperatureValidator(temperature));
        vitalSigns.setPulse(validator.pulseValidator(pulse));
        vitalSigns.setOxygenSaturation(validator.oxygenSaturationValidator(oxygenSaturation));
        return vitalSigns;
    }
}
