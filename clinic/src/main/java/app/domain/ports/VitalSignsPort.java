package app.domain.ports;

import java.util.List;
import app.domain.model.VitalSigns;

public interface VitalSignsPort {
    public VitalSigns save(VitalSigns vitalSigns) throws Exception;
    public List<VitalSigns> listByPatient (int documentPatient) throws Exception;
}
