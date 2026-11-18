package co.istad.lyhout.ecommerce.client.ecommerce.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason
) {
}
