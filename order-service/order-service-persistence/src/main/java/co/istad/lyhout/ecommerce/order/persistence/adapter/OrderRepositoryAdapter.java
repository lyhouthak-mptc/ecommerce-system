package co.istad.lyhout.ecommerce.order.persistence.adapter;

import co.istad.lyhout.ecommerce.order.domain.entity.Order;
import co.istad.lyhout.ecommerce.order.domain.port.output.OrderRepository;
import co.istad.lyhout.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;


    @Override
    public Order saveOrder(Order order) {
        return null;
    }

}
