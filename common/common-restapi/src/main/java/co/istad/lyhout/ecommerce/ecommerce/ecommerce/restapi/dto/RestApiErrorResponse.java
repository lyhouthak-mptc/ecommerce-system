package co.istad.lyhout.ecommerce.ecommerce.ecommerce.restapi.dto;

import lombok.Builder;

@Builder
public record RestApiErrorResponse<T>(
        String code,
        String message,
        T detail
) {
}
