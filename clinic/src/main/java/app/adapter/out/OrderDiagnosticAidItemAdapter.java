package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.OrderDiagnosticAidItem;
import app.domain.ports.OrderDiagnosticAidItemPort;
import app.infrastructure.persistence.repository.OrderDiagnosticAidItemRepository;
import app.infrastructure.persistence.mapper.OrderDiagnosticAidItemMapper;
import app.infrastructure.persistence.entities.OrderDiagnosticAidItemEntity;

@Service
public class OrderDiagnosticAidItemAdapter implements OrderDiagnosticAidItemPort {

    private final OrderDiagnosticAidItemRepository repository;
    private final OrderDiagnosticAidItemMapper mapper;

    public OrderDiagnosticAidItemAdapter(OrderDiagnosticAidItemRepository repository, OrderDiagnosticAidItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDiagnosticAidItem> listByNumberOrder(int numberOrder) throws Exception {
        return repository.findById_NumberOrder(numberOrder)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public OrderDiagnosticAidItem save(OrderDiagnosticAidItem orderDiagnosticAidItem) throws Exception {
        OrderDiagnosticAidItemEntity saved = repository.save(mapper.toEntity(orderDiagnosticAidItem));
        return mapper.toDomain(saved);
    }
}
