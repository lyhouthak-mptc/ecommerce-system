package co.istad.lyhout.ecommerce.order.persistence.adapter;

import co.istad.lyhout.ecommerce.order.domain.entity.Order;
import co.istad.lyhout.ecommerce.order.domain.port.output.OrderRepository;
import co.istad.lyhout.ecommerce.order.persistence.entity.OrderEntity;
import co.istad.lyhout.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.istad.lyhout.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;


    @Override
    public Order saveOrder(Order order) {

        // Map Order to OrderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        return orderPersistenceMapper.orderEntityToOrder(orderJpaRepository.save(orderEntity));

    }

}
