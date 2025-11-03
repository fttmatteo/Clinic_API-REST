package app.domain.model;

import java.sql.Timestamp;

/**
 * Representa la aplicación o realización de un ítem de una orden médica por
 * parte de una enfermera. Sirve para registrar cuándo un medicamento ha
 * sido administrado o un procedimiento realizado y en qué cantidad. No se
 * utiliza para ayudas diagnósticas, ya que estas no requieren aplicación
 * por parte de la enfermería.
 */
public class OrderExecutionRecord {
    private Long id;
    private OrderItem orderItem;
    private Employee nurse;
    private Timestamp dateTime;
    private Double amount;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Employee getNurse() {
        return nurse;
    }

    public void setNurse(Employee nurse) {
        this.nurse = nurse;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}