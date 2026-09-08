package co.istad.haklyhout.ecommerce.domain.valueobject;

import java.util.UUID;

public record StreetAddress(
        UUID id,
        String street,
        String postalCode,
        String city
) {

}
