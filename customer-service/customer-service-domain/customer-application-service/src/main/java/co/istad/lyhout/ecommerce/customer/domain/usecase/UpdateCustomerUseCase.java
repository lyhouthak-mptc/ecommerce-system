package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.dto.UpdateCustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.istad.lyhout.ecommerce.customer.domain.mapper.CustomerDataMapper;
import co.istad.lyhout.ecommerce.customer.domain.port.output.CustomerRepository;
import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.haklyhout.ecommerce.domain.valueobject.Email;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import co.istad.lyhout.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import co.istad.lyhout.ecommerce.customer.domain.service.CustomerDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Use case: change an existing customer's name, email and phone number.
// Called by the REST API controller (PUT /api/v1/customers/{customerId}).
// Flow: load customer -> check new email -> domain updates -> save -> return id
@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional
    public UpdateCustomerResult execute(UpdateCustomerCommand updateCustomerCommand) {
        log.info("Execute UpdateCustomerUseCase : {}", updateCustomerCommand);

        Customer customer = customerRepository.findById(new CustomerId(updateCustomerCommand.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: " + updateCustomerCommand.customerId()));

        Email newEmail = new Email(updateCustomerCommand.email());
        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail.value())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        CustomerUpdatedEvent customerUpdatedEvent = customerDomainService.updateCustomer(customer,
                updateCustomerCommand.familyName(),
                updateCustomerCommand.givenName(),
                newEmail,
                customerDataMapper.toPhoneNumber(updateCustomerCommand.phoneNumber()));
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer updated with id: {} at {}",
                savedCustomer.getId().value(), customerUpdatedEvent.getUpdatedAt());
        return new UpdateCustomerResult(savedCustomer.getId().value());
    }
}
