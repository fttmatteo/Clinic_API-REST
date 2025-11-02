package app.domain.ports;

import java.sql.Timestamp;
import java.util.List;

import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;

/**
 * Puerto de persistencia para las operaciones relacionadas con las citas. Permite
 * almacenar, consultar y eliminar citas, así como búsquedas por médico,
 * paciente y fecha.
 */
public interface AppointmentPort {
    void save(Appointment appointment) throws Exception;

    Appointment findById(Long id) throws Exception;

    List<Appointment> findByDoctorAndDateTime(Employee doctor, Timestamp dateTime) throws Exception;

    List<Appointment> findByPatient(Patient patient) throws Exception;

    List<Appointment> findByDoctor(Employee doctor) throws Exception;

    void delete(Appointment appointment) throws Exception;
}