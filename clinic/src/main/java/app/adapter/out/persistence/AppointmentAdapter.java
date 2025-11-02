package app.adapter.out.persistence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.ports.AppointmentPort;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.mapper.AppointmentMapper;
import app.infrastructure.persistence.mapper.EmployeeMapper;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.AppointmentRepository;

/**
 * Adaptador de infraestructura que implementa el puerto de persistencia para
 * citas. Convierte entre entidades JPA y objetos de dominio utilizando
 * mappers y delega las operaciones al repositorio Spring Data JPA.
 */
@Service
public class AppointmentAdapter implements AppointmentPort {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void save(Appointment appointment) throws Exception {
        AppointmentEntity entity = AppointmentMapper.toEntity(appointment);
        appointmentRepository.save(entity);
        appointment.setId(entity.getId());
    }

    @Override
    public Appointment findById(Long id) throws Exception {
        Optional<AppointmentEntity> optional = appointmentRepository.findById(id);
        return optional.map(AppointmentMapper::toDomain).orElse(null);
    }

    @Override
    public List<Appointment> findByDoctorAndDateTime(Employee doctor, Timestamp dateTime) throws Exception {
        List<AppointmentEntity> entities = appointmentRepository
                .findByDoctorAndDateTime(EmployeeMapper.toEntity(doctor), dateTime);
        return entities.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByPatient(Patient patient) throws Exception {
        List<AppointmentEntity> entities = appointmentRepository
                .findByPatient(PatientMapper.toEntity(patient));
        return entities.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctor(Employee doctor) throws Exception {
        List<AppointmentEntity> entities = appointmentRepository
                .findByDoctor(EmployeeMapper.toEntity(doctor));
        return entities.stream().map(AppointmentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(Appointment appointment) throws Exception {
        if (appointment.getId() != null) {
            appointmentRepository.deleteById(appointment.getId());
        }
    }
}