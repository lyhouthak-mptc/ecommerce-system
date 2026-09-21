package co.istad.lyhout.ecommerce.order.domain.port.output;

import co.istad.lyhout.ecommerce.order.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);

}
