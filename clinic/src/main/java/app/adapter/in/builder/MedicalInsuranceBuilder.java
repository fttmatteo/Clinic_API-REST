package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.MedicalInsuranceValidator;
import app.domain.model.MedicalInsurance;

@Component
public class MedicalInsuranceBuilder {

    @Autowired private MedicalInsuranceValidator validator;

    public MedicalInsurance build(
        String companyName,
        String numberPolicy,
        String statusPolicy,
        String endDatePolicy
    ) throws Exception {

        MedicalInsurance medicalInsurance = new MedicalInsurance();
        medicalInsurance.setCompanyName(validator.companyNameValidator(companyName));
        medicalInsurance.setNumberPolicy(validator.numberPolicyValidator(numberPolicy)); // long en tu modelo
        medicalInsurance.setStatusPolicy(validator.statusPolicyValidator(statusPolicy));
        medicalInsurance.setEndDatePolicy(validator.endDatePolicyValidator(endDatePolicy));
        return medicalInsurance;
    }
}
