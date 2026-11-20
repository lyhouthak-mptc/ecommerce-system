package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.customer.client.dto.UserResponse;
import co.istad.lyhout.ecommerce.customer.client.exception.UserServiceUnavailableException;
import co.istad.lyhout.ecommerce.customer.client.feign.UserFeignClient;
import co.istad.lyhout.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.dto.CreateCustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import co.istad.lyhout.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerDomainException;
import co.istad.lyhout.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.istad.lyhout.ecommerce.customer.domain.port.output.CustomerRepository;
import co.istad.lyhout.ecommerce.customer.domain.service.CustomerDomainService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerDomainService customerDomainService; // business rules (from customer-domain-core)
    private final CustomerRepository customerRepository;
    private final CustomerDomainMapper customerDomainMapper;

    private final UserFeignClient userFeignClient;

    @Transactional
    public CreateCustomerResult execute(CreateCustomerCommand createCustomerCommand) {
        log.info("Execute CreateCustomerUseCase : {}", createCustomerCommand);

        // ACL - verify user with user-service
        try {
            UserResponse userResponse = userFeignClient.getById(createCustomerCommand.userId());
            log.info("User found: {}", userResponse);
        } catch (FeignException.NotFound e) {
            throw new CustomerDomainException("User not found");
        } catch (UserServiceUnavailableException e) {
            throw new CustomerDomainException(e.getMessage());
        }

        if (customerRepository.existsByUsername(createCustomerCommand.username())) {
            throw new CustomerDomainException("Username already exists");
        }

        if (customerRepository.existsByEmail(createCustomerCommand.email())) {
            throw new CustomerDomainException("Email already exists");
        }

        Customer customer = customerDomainMapper.createCustomerCommandToCustomer(createCustomerCommand);
        CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(customer);
        CustomerResult savedCustomer = customerRepository.save(customer);

        if (savedCustomer == null) {
            log.error("Could not save customer with id: {}", createCustomerCommand.customerId());
            throw new CustomerDomainException("Could not save customer with id " + createCustomerCommand.customerId());
        }

        return new CreateCustomerResult(savedCustomer.id());
    }
}
