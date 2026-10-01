package co.istad.lyhout.ecommerce.customer.domain.exception;

import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerDomainException;

public class CustomerNotFoundException extends CustomerDomainException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
