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