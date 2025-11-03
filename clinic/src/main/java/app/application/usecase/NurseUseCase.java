package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.VitalSignsRecord;
import app.domain.services.CreateVitalSignsRecord;

/**
 * Caso de uso para las funciones de las enfermeras. Permite registrar
 * signos vitales de un paciente.
 */
@Service
public class NurseUseCase {

    @Autowired
    private CreateVitalSignsRecord createVitalSignsRecord;

    @Autowired
    private app.domain.services.CreateOrderExecutionRecord createOrderExecutionRecord;

    public void recordVitalSigns(VitalSignsRecord record) throws Exception {
        createVitalSignsRecord.create(record);
    }

    public void executeOrderItem(app.domain.model.OrderExecutionRecord record, Long orderId, Integer itemNumber) throws Exception {
        createOrderExecutionRecord.create(record, orderId, itemNumber);
    }
}