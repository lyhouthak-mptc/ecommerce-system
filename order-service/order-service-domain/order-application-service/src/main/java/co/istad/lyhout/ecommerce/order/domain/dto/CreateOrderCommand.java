package co.istad.lyhout.ecommerce.order.domain.dto;

import co.istad.haklyhout.ecommerce.domain.valueobject.BusinessId;
import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.haklyhout.ecommerce.domain.valueobject.Money;
import co.istad.haklyhout.ecommerce.domain.valueobject.StreetAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
        UUID customerId,
        UUID businessId,
        BigDecimal price,
        CommandOrderAddress deliveryAddress,
        List<CommandOrderItem> items
) {
}
