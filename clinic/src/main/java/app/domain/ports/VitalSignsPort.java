package app.domain.ports;

import java.util.List;
import app.domain.model.VitalSigns;

public interface VitalSignsPort {
    VitalSigns save(VitalSigns vitalSigns) throws Exception;
    List<VitalSigns> listByPatient(Integer documentPatient) throws Exception;
}
