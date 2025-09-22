package app.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.domain.model.OrderMedicationItem;
import app.domain.ports.OrderMedicationItemPort;
import app.infrastructure.persistence.repository.OrderMedicationItemRepository;
import app.infrastructure.persistence.mapper.OrderMedicationItemMapper;
import app.infrastructure.persistence.entities.OrderMedicationItemEntity;

@Service
public class OrderMedicationItemAdapter implements OrderMedicationItemPort {

    private final OrderMedicationItemRepository repository;
    private final OrderMedicationItemMapper mapper;

    public OrderMedicationItemAdapter(OrderMedicationItemRepository repository, OrderMedicationItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderMedicationItem> listByNumberOrder(int numberOrder) throws Exception {
        return repository.findById_NumberOrder(numberOrder)
                         .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public OrderMedicationItem save(OrderMedicationItem orderMedicationItem) throws Exception {
        OrderMedicationItemEntity saved = repository.save(mapper.toEntity(orderMedicationItem));
        return mapper.toDomain(saved);
    }
}
