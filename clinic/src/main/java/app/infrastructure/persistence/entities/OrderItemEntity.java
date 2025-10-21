package app.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Entidad base JPA que representa un ítem dentro de una orden médica. Esta
 * clase solo contiene los atributos comunes a todos los tipos de ítems.
 * La información específica de medicamentos, procedimientos y ayudas
 * diagnósticas se almacena en subclases que extienden de esta entidad.
 * Además, se define una restricción de unicidad sobre el par
 * (order_id, item_number) para garantizar que no haya dos ítems con el
 * mismo número dentro de una misma orden.
 */
@Entity
@Table(
    name = "order_items",
    uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "item_number"})
)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class OrderItemEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double cost;

    @Column
    private Boolean requiresSpecialist;

    @Column
    private String specialistTypeId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private MedicalOrderEntity order;

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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public MedicalOrderEntity getOrder() {
        return order;
    }
    public void setOrder(MedicalOrderEntity order) {
        this.order = order;
    }
}