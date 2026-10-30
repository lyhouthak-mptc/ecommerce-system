package co.istad.lyhout.ecommerce.user.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserResponse(
        String id,
        String username,
        String email,
        String familyName,
        String givenName,
        String gender,
        Date birthday,
        String mobilePhone,
        String address
) {
}
