package app.adapter.in.builder;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.AppointmentValidator;
import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;

/**
 * Builder para la entidad {@link Appointment}. Construye la cita a partir
 * de los datos de la solicitud y delega la validación a
 * {@link AppointmentValidator}.
 */
@Component
public class AppointmentBuilder {

    @Autowired
    private AppointmentValidator appointmentValidator;

    public Appointment build(String patientId, String doctorDocument, String dateTime) throws Exception {
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.setId(appointmentValidator.patientIdValidator(patientId));
        appointment.setPatient(patient);
        Employee doctor = new Employee();
        doctor.setDocument(appointmentValidator.doctorDocumentValidator(doctorDocument));
        appointment.setDoctor(doctor);
        Timestamp ts = appointmentValidator.dateTimeValidator(dateTime);
        appointment.setDateTime(ts);
        appointment.setStatus("SCHEDULED");
        return appointment;
    }
}