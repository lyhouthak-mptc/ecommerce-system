package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerDomainException;
import co.istad.lyhout.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.istad.lyhout.ecommerce.customer.domain.port.output.CustomerRepository;
import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerDomainMapper customerDomainMapper;

    public CustomerResult execute(UUID id) {
        return customerRepository.findByIdWithCache(new CustomerId(id))
                .orElseThrow(() -> new CustomerDomainException("Customer not found"));
    }

}
