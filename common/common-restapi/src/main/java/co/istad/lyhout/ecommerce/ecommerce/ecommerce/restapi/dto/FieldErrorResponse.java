package co.istad.lyhout.ecommerce.ecommerce.ecommerce.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason
) {
}
