package co.istad.lyhout.ecommerce.order.domain.dto;

import co.istad.haklyhout.ecommerce.domain.valueobject.OrderId;

public record CreateOrderResponse(
        OrderId orderId
) {

}
