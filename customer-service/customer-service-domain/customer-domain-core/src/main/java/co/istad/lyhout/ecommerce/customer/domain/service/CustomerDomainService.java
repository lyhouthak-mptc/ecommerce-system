package co.istad.lyhout.ecommerce.customer.domain.service;

import co.istad.haklyhout.ecommerce.domain.valueobject.Email;
import co.istad.haklyhout.ecommerce.domain.valueobject.PhoneNumber;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import co.istad.lyhout.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.istad.lyhout.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import co.istad.lyhout.ecommerce.customer.domain.event.CustomerUpdatedEvent;


public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
