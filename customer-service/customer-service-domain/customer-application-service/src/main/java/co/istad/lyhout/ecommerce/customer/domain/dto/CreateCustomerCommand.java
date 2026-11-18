package co.istad.lyhout.ecommerce.customer.domain.dto;

public record CreateCustomerCommand(
        String userId,
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
