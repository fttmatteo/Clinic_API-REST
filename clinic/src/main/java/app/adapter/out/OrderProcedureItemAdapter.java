package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.OrderProcedureItem;
import app.domain.ports.OrderProcedureItemPort;
import app.infrastructure.persistence.repository.OrderProcedureItemRepository;
import app.infrastructure.persistence.mapper.OrderProcedureItemMapper;
import app.infrastructure.persistence.entities.OrderProcedureItemEntity;

@Service
public class OrderProcedureItemAdapter implements OrderProcedureItemPort {

    private final OrderProcedureItemRepository repository;
    private final OrderProcedureItemMapper mapper;

    public OrderProcedureItemAdapter(OrderProcedureItemRepository repository, OrderProcedureItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderProcedureItem> listByNumberOrder(int numberOrder) throws Exception {
        return repository.findById_NumberOrder(numberOrder)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public OrderProcedureItem save(OrderProcedureItem orderProcedureItem) throws Exception {
        OrderProcedureItemEntity saved = repository.save(mapper.toEntity(orderProcedureItem));
        return mapper.toDomain(saved);
    }
}
