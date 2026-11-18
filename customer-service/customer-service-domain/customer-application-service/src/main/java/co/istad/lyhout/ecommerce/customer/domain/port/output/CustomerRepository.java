package co.istad.lyhout.ecommerce.customer.domain.port.output;


import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.lyhout.ecommerce.core.PageResponse;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {
    PageResponse<CustomerResult> findAll(int page, int size);
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    Optional<Customer> findCustomerByUserId(String userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
