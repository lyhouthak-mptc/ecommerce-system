package co.istad.lyhout.ecommerce.customer.domain.mapper;

import co.istad.lyhout.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDomainMapper {

    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "phoneNumber", target = "phoneNumber.value")
    Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand);

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phoneNumber.value", target = "phoneNumber")
    CustomerResult toCustomerResult(Customer customer);

}
