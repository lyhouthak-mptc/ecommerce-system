package co.istad.lyhout.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.util.Date;

// Partial update: null fields are left unchanged
public record UserUpdateRequest(
        @Email String email,
        String familyName,
        String givenName,
        String gender,
        @Past Date birthday,
        String mobilePhone,
        String address
) {
}
