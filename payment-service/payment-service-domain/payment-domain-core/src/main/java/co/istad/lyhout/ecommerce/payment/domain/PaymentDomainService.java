package co.istad.lyhout.ecommerce.payment.domain;

import co.istad.lyhout.ecommerce.payment.domain.entity.CreditEntry;
import co.istad.lyhout.ecommerce.payment.domain.entity.CreditHistory;
import co.istad.lyhout.ecommerce.payment.domain.entity.Payment;
import co.istad.lyhout.ecommerce.payment.domain.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages);
}
