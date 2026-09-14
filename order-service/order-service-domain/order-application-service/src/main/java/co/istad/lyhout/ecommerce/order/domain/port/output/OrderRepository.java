package co.istad.lyhout.ecommerce.order.domain.port.output;

import co.istad.lyhout.ecommerce.order.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);

}
