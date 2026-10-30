package co.istad.lyhout.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreateRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String roleId, // use , as delimiter
        @NotBlank String familyName,
        @NotBlank String givenName,
        @NotBlank
        String password,
        @NotBlank
        String confirmedPassword,
        @NotBlank
        String roleName // use , as delimiter
) {
}
