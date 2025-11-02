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

    /**
     * Registra la administración de un ítem de una orden médica (medicamento
     * o procedimiento) por parte de la enfermería. Se requiere el id de la
     * orden y el número de ítem para localizar la prescripción, así como
     * los datos del registro de aplicación (nurse, cantidad y notas).
     *
     * @param record   información de aplicación (enfermera, cantidad, notas)
     * @param orderId  identificador de la orden médica
     * @param itemNumber número de ítem dentro de la orden
     * @throws Exception si ocurre un error o la operación no es válida
     */
    public void executeOrderItem(app.domain.model.OrderExecutionRecord record, Long orderId, Integer itemNumber) throws Exception {
        createOrderExecutionRecord.create(record, orderId, itemNumber);
    }
}