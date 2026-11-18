package co.istad.lyhout.ecommerce.payment.domain.exception;

import co.istad.haklyhout.ecommerce.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
