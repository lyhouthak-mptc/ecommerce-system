package co.istad.lyhout.ecommerce.order.domain.service;

import co.istad.lyhout.ecommerce.order.domain.entity.Business;
import co.istad.lyhout.ecommerce.order.domain.entity.Order;
import co.istad.lyhout.ecommerce.order.domain.event.OrderCancelledEvent;
import co.istad.lyhout.ecommerce.order.domain.event.OrderCreatedEvent;
import co.istad.lyhout.ecommerce.order.domain.event.OrderPaidEvent;
import co.istad.lyhout.ecommerce.order.domain.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService{

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
//      validate business
        if (!business.isActive()) {
            throw new OrderDomainException("Business is not currenlty action");
        }
        order.validateOrder();
        order.initializeOrder();

        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }

}