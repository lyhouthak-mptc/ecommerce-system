package co.istad.lyhout.ecommerce.customer.domain.dto;

public record GetCustomersQuery(
        int page,
        int size
) {
}
