package co.istad.lyhout.ecommerce.persistence.adapter;

import co.istad.lyhout.ecommerce.domain.entity.Order;
import co.istad.lyhout.ecommerce.domain.port.output.OrderRepository;
import co.istad.lyhout.ecommerce.persistence.repository.OrderJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

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
