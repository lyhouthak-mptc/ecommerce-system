package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.customer.domain.dto.DeleteCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.port.output.CustomerRepository;
import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public void execute(DeleteCustomerCommand deleteCustomerCommand) {
        CustomerId customerId = new CustomerId(deleteCustomerCommand.customerId());
        customerRepository.deleteById(customerId);
    }

}
