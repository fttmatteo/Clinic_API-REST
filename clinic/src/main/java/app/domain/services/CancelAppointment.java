package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Appointment;
import app.domain.ports.AppointmentPort;

/**
 * Servicio de dominio para cancelar citas existentes. Marca la cita como
 * cancelada y la persiste de nuevo.
 */
@Service
public class CancelAppointment {

    @Autowired
    private AppointmentPort appointmentPort;

    public void cancel(Long appointmentId) throws Exception {
        Appointment existing = appointmentPort.findById(appointmentId);
        if (existing == null) {
            throw new BusinessException("la cita a cancelar no existe");
        }
        existing.setStatus("CANCELLED");
        appointmentPort.save(existing);
    }
}