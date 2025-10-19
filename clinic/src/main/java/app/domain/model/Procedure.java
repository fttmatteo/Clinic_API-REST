package app.domain.model;

/**
 * Representa un procedimiento médico disponible en el inventario de la
 * clínica.  Al igual que los medicamentos, cada procedimiento posee
 * un identificador único, un nombre y un costo de referencia.  Los
 * procedimientos pueden requerir la intervención de un especialista.
 */
public class Procedure {
    private String id;
    private String name;
    private Double cost;

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
}