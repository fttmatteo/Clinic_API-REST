package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.ports.AppointmentPort;

/**
 * Servicio de dominio para consultar citas. Permite obtener las citas de un
 * paciente o de un médico.
 */
@Service
public class SearchAppointments {

    @Autowired
    private AppointmentPort appointmentPort;

    public List<Appointment> byPatient(Patient patient) throws Exception {
        return appointmentPort.findByPatient(patient);
    }

    public List<Appointment> byDoctor(Employee doctor) throws Exception {
        return appointmentPort.findByDoctor(doctor);
    }
}