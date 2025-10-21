package app.infrastructure.persistence.mapper;

import app.domain.model.Employee;
import app.domain.model.Invoice;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.infrastructure.persistence.entities.EmployeeEntity;
import app.infrastructure.persistence.entities.InvoiceEntity;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.PatientEntity;

/**
 * Mapper para convertir entre {@link Invoice} del dominio y
 * {@link InvoiceEntity} de la capa de persistencia. Maneja las
 * relaciones opcionales con médicos y órdenes médicas.
 */
public class InvoiceMapper {
    public static InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null) return null;
        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(invoice.getId());
        if (invoice.getPatient() != null) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setId(invoice.getPatient().getId());
            entity.setPatient(patientEntity);
        }
        if (invoice.getDoctor() != null) {
            EmployeeEntity doctorEntity = new EmployeeEntity();
            doctorEntity.setId(invoice.getDoctor().getId());
            entity.setDoctor(doctorEntity);
        }
        entity.setProductName(invoice.getProductName());
        entity.setProductAmount(invoice.getProductAmount());
        entity.setMedicine(invoice.isMedicine());
        entity.setCopay(invoice.getCopay());
        entity.setBilledToInsurer(invoice.getBilledToInsurer());
        if (invoice.getOrder() != null) {
            MedicalOrderEntity orderEntity = new MedicalOrderEntity();
            orderEntity.setId(invoice.getOrder().getId());
            entity.setOrder(orderEntity);
        }
        entity.setDate(invoice.getDate());
        return entity;
    }

    public static Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) return null;
        Invoice invoice = new Invoice();
        invoice.setId(entity.getId());
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            invoice.setPatient(patient);
        }
        if (entity.getDoctor() != null) {
            Employee doctor = new Employee();
            doctor.setId(entity.getDoctor().getId());
            invoice.setDoctor(doctor);
        }
        invoice.setProductName(entity.getProductName());
        invoice.setProductAmount(entity.getProductAmount());
        invoice.setMedicine(entity.getMedicine());
        invoice.setCopay(entity.getCopay());
        invoice.setBilledToInsurer(entity.getBilledToInsurer());
        if (entity.getOrder() != null) {
            MedicalOrder order = new MedicalOrder();
            order.setId(entity.getOrder().getId());
            invoice.setOrder(order);
        }
        invoice.setDate(entity.getDate());
        return invoice;
    }
}