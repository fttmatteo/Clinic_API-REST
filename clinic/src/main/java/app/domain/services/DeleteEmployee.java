package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.ports.EmployeePort;

/**
 * Servicio de dominio responsable de eliminar empleados de la clínica. Se
 * encarga de delegar la eliminación al puerto de persistencia y de
 * transformar las excepciones técnicas en excepciones de negocio más
 * comprensibles para las capas superiores.
 */
@Service
public class DeleteEmployee {

    @Autowired
    private EmployeePort employeePort;

    public void deleteByDocument(long document) throws Exception {
        try {
            employeePort.deleteByDocument(document);
        } catch (DataIntegrityViolationException dive) {
            throw new BusinessException(
                "no se puede eliminar el usuario porque tiene registros asociados en el sistema"
            );
        } catch (Exception e) {
            throw e;
        }
    }
}