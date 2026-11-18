package co.istad.lyhout.ecommerce.client.ecommerce.order.domain.dto;

import co.istad.haklyhout.ecommerce.domain.valueobject.OrderId;

import java.util.UUID;

public record CreateOrderResult(
        UUID orderId
) {

}
