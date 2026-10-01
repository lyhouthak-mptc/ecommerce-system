package co.istad.lyhout.ecommerce.payment.domain.service;

import co.istad.haklyhout.ecommerce.domain.valueobject.PaymentStatus;
import co.istad.lyhout.ecommerce.payment.domain.entity.CreditEntry;
import co.istad.lyhout.ecommerce.payment.domain.entity.CreditHistory;
import co.istad.lyhout.ecommerce.payment.domain.entity.Payment;

public interface PaymentDomainService {
  CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry);

  void updatePaymentStatus(Payment payment, PaymentStatus newPaymentStatus);
}
