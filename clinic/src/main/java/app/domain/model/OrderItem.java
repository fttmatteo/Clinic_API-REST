package app.domain.model;

import app.domain.model.enums.OrderItemType;

public class OrderItem {
    private Long itemId;                    // Número del ítem en la orden
    private Long orderId;               // Relación con la orden (ClinicalOrder)
    private OrderItemType type;         // Tipo de ítem: MEDICINE, PROCEDURE, DIAGNOSTIC_AID
    private String name;                // Nombre del medicamento, procedimiento o examen
    private String dose;                // Dosis (solo para medicamentos)
    private String duration;            // Duración del tratamiento (solo medicamentos)
    private Integer quantity;           // Cantidad (procedimientos o ayudas diagnósticas)
    private String frequency;           // Frecuencia (medicamentos/procedimientos)
    private boolean specialistRequired; // Indica si requiere especialista
    private String specialistType;      // Tipo de especialista (si aplica)
    private Double cost;                // Costo del ítem

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderItemType getType() {
        return type;
    }

    public void setType(OrderItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isSpecialistRequired() {
        return specialistRequired;
    }

    public void setSpecialistRequired(boolean specialistRequired) {
        this.specialistRequired = specialistRequired;
    }

    public String getSpecialistType() {
        return specialistType;
    }

    public void setSpecialistType(String specialistType) {
        this.specialistType = specialistType;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}
