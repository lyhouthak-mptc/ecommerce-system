package co.istad.lyhout.ecommerce.customer.domain.mapper;

import co.istad.lyhout.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.haklyhout.ecommerce.domain.valueobject.Email;
import co.istad.haklyhout.ecommerce.domain.valueobject.PhoneNumber;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerDataMapper {

    // id and status are not set here: the domain sets them in initiateCustomer()
    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return Customer.builder()
                .userId(createCustomerCommand.userId())
                .username(createCustomerCommand.username())
                .familyName(createCustomerCommand.familyName())
                .givenName(createCustomerCommand.givenName())
                .email(new Email(createCustomerCommand.email()))
                .phoneNumber(toPhoneNumber(createCustomerCommand.phoneNumber()))
                .build();
    }

    public CustomerResult customerToCustomerResult(Customer customer) {
        return new CustomerResult(
                customer.getId().value(),
                customer.getFamilyName(),
                customer.getGivenName(),
                customer.getEmail() == null ? null : customer.getEmail().value(),
                customer.getPhoneNumber() == null ? null : customer.getPhoneNumber().number(),
                0,
                0
        );
    }

    public PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }

}
