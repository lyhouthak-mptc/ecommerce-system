package co.istad.lyhout.ecommerce.domain.port.output;

import co.istad.lyhout.ecommerce.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);

}
