package co.istad.lyhout.ecommerce.order.domain.event;

import co.istad.lyhout.ecommerce.order.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
