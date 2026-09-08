package co.istad.lyhout.ecommerce.domain.event;

import co.istad.haklyhout.ecommerce.domain.event.DomainEvent;
import co.istad.lyhout.ecommerce.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
