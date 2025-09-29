package app.domain.model.inventory;

/**
 * Representa un procedimiento médico disponible en el inventario de la
 * clínica.  Al igual que los medicamentos, cada procedimiento posee
 * un identificador único, un nombre y un costo de referencia.  Los
 * procedimientos pueden requerir la intervención de un especialista.
 */
public class Procedure {
    private String id;
    private String name;
    private double cost;

    public Procedure() {
    }

    public Procedure(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}