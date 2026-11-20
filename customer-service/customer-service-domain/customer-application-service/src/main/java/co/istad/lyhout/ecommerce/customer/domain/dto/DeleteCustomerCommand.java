package co.istad.lyhout.ecommerce.customer.domain.dto;

import java.util.UUID;

public record DeleteCustomerCommand(
        UUID customerId
) {
}
