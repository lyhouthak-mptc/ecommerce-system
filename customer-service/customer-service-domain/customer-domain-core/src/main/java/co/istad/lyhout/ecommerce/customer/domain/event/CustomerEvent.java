package co.istad.lyhout.ecommerce.customer.domain.event;

import co.istad.haklyhout.ecommerce.domain.event.DomainEvent;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;

public abstract class CustomerEvent implements DomainEvent<Customer> {
    private final Customer customer;

    public CustomerEvent(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
