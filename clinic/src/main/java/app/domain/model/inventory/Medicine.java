package app.domain.model.inventory;

/**
 * Representa un medicamento disponible en el inventario de la clínica.
 * Cada medicamento tiene un identificador único, un nombre y un costo
 * asociado.  Esta clase simple forma parte del catálogo de insumos que
 * pueden ser recetados en una orden médica.  El costo corresponde a
 * una tarifa de referencia que puede ser utilizada al momento de
 * generar facturas o estimar costos en la historia clínica.
 */
public class Medicine {
    private String id;
    private String name;
    private double cost;

    public Medicine() {
    }

    public Medicine(String id, String name, double cost) {
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