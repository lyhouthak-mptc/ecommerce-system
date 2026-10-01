package co.istad.lyhout.ecommerce.customer.domain.dto;

public record CreateCustomerCommand(
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
