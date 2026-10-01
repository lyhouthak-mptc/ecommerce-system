package co.istad.lyhout.ecommerce.payment.domain.exception;

import co.istad.haklyhout.ecommerce.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
  public PaymentDomainException(String message) {
    super(message);
  }

  public PaymentDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
