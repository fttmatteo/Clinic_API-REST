package app.adapter.in.rest.request;

/**
 * Representa los datos minimos de un item dentro de una orden medica.
 * El medico solo debe enviar el tipo de producto y el identificador
 * del elemento en el inventario.
 */
public class OrderItemRequest {
    private String type;
    private String referenceId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}