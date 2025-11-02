package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.application.exceptions.BusinessException;
import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.enums.Role;
import app.domain.ports.AppointmentPort;
import app.domain.ports.EmployeePort;
import app.domain.ports.PatientPort;

/**
 * Servicio de dominio responsable de crear nuevas citas. Aplica reglas de
 * negocio como la verificación de existencia de médico y paciente y que
 * el médico no tenga otra cita programada en la misma fecha y hora.
 */
@Service
public class CreateAppointment {

    @Autowired
    private AppointmentPort appointmentPort;

    @Autowired
    private PatientPort patientPort;

    @Autowired
    private EmployeePort employeePort;

    public void create(Appointment appointment) throws Exception {
        Patient patient = patientPort.findByDocument(appointment.getPatient());
        if (patient == null) {
            throw new BusinessException("el paciente de la cita no existe");
        }
        appointment.setPatient(patient);
        Employee doctor = employeePort.findByDocument(appointment.getDoctor());
        if (doctor == null || !Role.DOCTOR.equals(doctor.getRole())) {
            throw new BusinessException("el médico de la cita no es válido");
        }
        appointment.setDoctor(doctor);
        List<Appointment> existing = appointmentPort.findByDoctorAndDateTime(doctor, appointment.getDateTime());
        if (existing != null && !existing.isEmpty()) {
            throw new BusinessException("el médico ya tiene una cita en esa fecha y hora");
        }
        if (appointment.getStatus() == null) {
            appointment.setStatus("SCHEDULED");
        }
        appointmentPort.save(appointment);
    }
}
