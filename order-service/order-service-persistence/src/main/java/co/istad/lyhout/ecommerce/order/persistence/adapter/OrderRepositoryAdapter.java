package co.istad.lyhout.ecommerce.order.persistence.adapter;

import co.istad.lyhout.ecommerce.order.domain.entity.Order;
import co.istad.lyhout.ecommerce.order.domain.port.output.OrderRepository;
import co.istad.lyhout.ecommerce.order.persistence.repository.OrderJpaRepository;

public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return null;
    }

}
