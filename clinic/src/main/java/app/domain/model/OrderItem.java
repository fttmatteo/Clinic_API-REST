package app.domain.model;

import app.domain.model.enums.OrderItemType;

/**
 * Representa un ítem dentro de una orden médica. Dependiendo del tipo de
 * ítem (medicamento, procedimiento o ayuda diagnóstica) algunos campos
 * tendrán valor y otros no. Los números de ítem dentro de una orden deben
 * comenzar en 1 y ser únicos. Cuando el ítem corresponde a un procedimiento
 * o ayuda diagnóstica, puede requerir la Integerervención de un especialista y
 * asociarse a un tipo de especialidad.
 */
public class OrderItem {
    private Long id;
    private Integer itemNumber;
    private OrderItemType type;
    private String name;
    private String dose;
    private String treatmentDuration;
    private Integer quantity;
    private String frequency;
    private Double cost;
    private Boolean requiresSpecialist;
    private String specialistTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
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

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(Boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }

    public String getSpecialistTypeId() {
        return specialistTypeId;
    }

    public void setSpecialistTypeId(String specialistTypeId) {
        this.specialistTypeId = specialistTypeId;
    }
}