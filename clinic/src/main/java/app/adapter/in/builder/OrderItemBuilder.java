package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.OrderItemValidator;
import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;

/**
 * Builder para construir instancias de {@link OrderItem} a partir de los
 * datos recibidos en una peticion. Solo valida el tipo del item y el
 * identificador del insumo, el resto de informacion se completa desde el
 * inventario.
 */
@Component
public class OrderItemBuilder {
    @Autowired
    private OrderItemValidator validator;

    public OrderItem build(String type, String referenceId, int itemNumber) throws Exception {
        OrderItem item = new OrderItem();
        OrderItemType itemType = validator.typeValidator(type);
        item.setItemNumber(itemNumber);
        item.setType(itemType);
        item.setName(validator.referenceIdValidator(referenceId));
        return item;
    }
}