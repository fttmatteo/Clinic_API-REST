package app.domain.model;

/**
 * Representa una ayuda diagnóstica (como análisis de laboratorio o
 * exámenes de imagen) disponible en el inventario de la clínica.  Cada
 * ayuda diagnóstica tiene un identificador, un nombre y un costo
 * asociado.  Las ayudas diagnósticas se recetan a través de las
 * órdenes médicas cuando se requieren pruebas adicionales antes de
 * determinar un tratamiento definitivo.
 */
public class DiagnosticAid {
    private String id;
    private String name;
    private Double cost;
    private Integer defaultQuantity;
    private Boolean defaultRequiresSpecialist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getDefaultQuantity() {
        return defaultQuantity;
    }

    public void setDefaultQuantity(Integer defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

    public Boolean getDefaultRequiresSpecialist() {
        return defaultRequiresSpecialist;
    }

    public void setDefaultRequiresSpecialist(Boolean defaultRequiresSpecialist) {
        this.defaultRequiresSpecialist = defaultRequiresSpecialist;
    }
}
