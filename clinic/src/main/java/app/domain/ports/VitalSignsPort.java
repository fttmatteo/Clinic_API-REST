package app.domain.ports;

import app.domain.model.VitalSignsRecord;

/**
 * Puerto de persistencia para los registros de signos vitales. Permite
 * almacenar los registros tomados por las enfermeras.
 */
public interface VitalSignsPort {
    void save(VitalSignsRecord record) throws Exception;
}