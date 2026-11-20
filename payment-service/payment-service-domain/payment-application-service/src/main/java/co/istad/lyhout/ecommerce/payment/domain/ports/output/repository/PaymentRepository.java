package co.istad.lyhout.ecommerce.payment.domain.ports.output.repository;

import co.istad.lyhout.ecommerce.payment.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
