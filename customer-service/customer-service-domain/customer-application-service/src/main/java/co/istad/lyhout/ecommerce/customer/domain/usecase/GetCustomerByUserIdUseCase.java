package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.istad.lyhout.ecommerce.customer.domain.mapper.CustomerDataMapper;
import co.istad.lyhout.ecommerce.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Use case: load one customer by the user id from user-service.
// Called by the REST API controller (GET /api/customers/user/{userId}).
@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerByUserIdUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional(readOnly = true)
    public CustomerResult execute(String userId) {
        log.info("Execute GetCustomerByUserIdUseCase : {}", userId);

        return customerRepository.findCustomerByUserId(userId)
                .map(customerDataMapper::customerToCustomerResult)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found for userId: " + userId));
    }
}
