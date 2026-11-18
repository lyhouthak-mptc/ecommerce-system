package co.istad.lyhout.ecommerce.ecommerce.ecommerce.order.domain.dto;

public record CommandOrderAddress(
        String street,
        String postalCode,
        String city
) {
}
