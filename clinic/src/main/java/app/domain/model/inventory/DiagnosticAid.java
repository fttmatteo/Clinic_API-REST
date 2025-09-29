package app.domain.model.inventory;

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
    private double cost;

    public DiagnosticAid() {
    }

    public DiagnosticAid(String id, String name, double cost) {
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