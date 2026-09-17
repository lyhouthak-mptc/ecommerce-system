package co.istad.lyhout.ecommerce.order.domain.exception;

import co.istad.haklyhout.ecommerce.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
