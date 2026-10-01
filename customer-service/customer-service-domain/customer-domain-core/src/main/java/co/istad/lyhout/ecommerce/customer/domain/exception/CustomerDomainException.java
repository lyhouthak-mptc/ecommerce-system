package co.istad.lyhout.ecommerce.customer.domain.exception;

import co.istad.haklyhout.ecommerce.domain.exception.DomainException;

public class CustomerDomainException  extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }

    public CustomerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
