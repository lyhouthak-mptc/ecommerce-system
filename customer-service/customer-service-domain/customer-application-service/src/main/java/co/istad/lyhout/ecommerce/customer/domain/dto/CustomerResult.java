package co.istad.lyhout.ecommerce.customer.domain.dto;

import java.util.UUID;

public record CustomerResult(
        UUID id,
        String familyName,
        String givenName,
        String email,
        String phoneNumber,
        int totalPage,
        int totalSize
) {
}
