package co.istad.lyhout.ecommerce.client.ecommerce.order.restapi.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderCreateResponse(
        UUID orderId
) {
}
