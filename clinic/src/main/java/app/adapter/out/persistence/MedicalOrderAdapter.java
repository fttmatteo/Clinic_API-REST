package app.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.ports.MedicalOrderPort;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.MedicalOrderMapper;
import app.infrastructure.persistence.repository.MedicalOrderRepository;

/**
 * Adaptador de infraestructura que implementa el puerto
 * {@link MedicalOrderPort}. Proporciona métodos para guardar y
 * consultar órdenes médicas utilizando JPA.
 */
@Service
public class MedicalOrderAdapter implements MedicalOrderPort {
    @Autowired
    private MedicalOrderRepository orderRepository;

    @Override
    public void save(MedicalOrder order) throws Exception {
        MedicalOrderEntity entity = MedicalOrderMapper.toEntity(order);
        orderRepository.save(entity);
        order.setId(entity.getId());
        order.setCreationDate(entity.getCreationDate());
    }

    @Override
    public MedicalOrder findById(MedicalOrder order) throws Exception {
        Optional<MedicalOrderEntity> opt = orderRepository.findById(order.getId());
        return opt.map(MedicalOrderMapper::toDomain).orElse(null);
    }

    @Override
    public List<MedicalOrder> findByPatient(Patient patient) throws Exception {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        List<MedicalOrderEntity> entities = orderRepository.findByPatient(patientEntity);
        List<MedicalOrder> orders = new ArrayList<>();
        for (MedicalOrderEntity entity : entities) {
            orders.add(MedicalOrderMapper.toDomain(entity));
        }
        return orders;
    }
    @Override
    public MedicalOrder findByOrderNumber(String orderNumber) throws Exception {
        Optional<MedicalOrderEntity> opt = orderRepository.findByOrderNumber(orderNumber);
        return opt.map(MedicalOrderMapper::toDomain).orElse(null);
    }
}