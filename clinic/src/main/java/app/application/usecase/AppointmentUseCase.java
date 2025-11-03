package app.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.services.CancelAppointment;
import app.domain.services.CreateAppointment;
import app.domain.services.SearchAppointments;

/**
 * Caso de uso para operaciones relacionadas con las citas médicas. Facilita
 * la interacción de las capas superiores con los servicios de dominio para
 * crear, cancelar y consultar citas.
 */
@Service
public class AppointmentUseCase {

    @Autowired
    private CreateAppointment createAppointment;

    @Autowired
    private CancelAppointment cancelAppointment;

    @Autowired
    private SearchAppointments searchAppointments;

    public void createAppointment(Appointment appointment) throws Exception {
        createAppointment.create(appointment);
    }

    public void cancelAppointment(Long id) throws Exception {
        cancelAppointment.cancel(id);
    }

    public List<Appointment> listByPatient(Patient patient) throws Exception {
        return searchAppointments.byPatient(patient);
    }

    public List<Appointment> listByDoctor(Employee doctor) throws Exception {
        return searchAppointments.byDoctor(doctor);
    }
}