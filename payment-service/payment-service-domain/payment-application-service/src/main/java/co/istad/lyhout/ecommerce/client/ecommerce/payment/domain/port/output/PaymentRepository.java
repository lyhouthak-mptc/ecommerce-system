package co.istad.lyhout.ecommerce.client.ecommerce.payment.domain.port.output;

import co.istad.lyhout.ecommerce.payment.domain.entity.Payment;

public interface PaymentRepository {
  Payment savePayment(Payment payment);
}
