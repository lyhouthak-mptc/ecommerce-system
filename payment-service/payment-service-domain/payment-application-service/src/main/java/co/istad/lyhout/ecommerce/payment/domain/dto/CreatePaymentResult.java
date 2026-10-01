package co.istad.lyhout.ecommerce.payment.domain.dto;

import co.istad.haklyhout.ecommerce.domain.valueobject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
    UUID paymentId,
    PaymentStatus paymentStatus
) {
}
