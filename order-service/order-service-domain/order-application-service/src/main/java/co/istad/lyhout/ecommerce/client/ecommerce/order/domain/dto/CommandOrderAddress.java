package co.istad.lyhout.ecommerce.client.ecommerce.order.domain.dto;

public record CommandOrderAddress(
        String street,
        String postalCode,
        String city
) {
}
