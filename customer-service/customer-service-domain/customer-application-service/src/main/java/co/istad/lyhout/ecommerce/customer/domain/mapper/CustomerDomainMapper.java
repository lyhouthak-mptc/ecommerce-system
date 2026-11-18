package co.istad.lyhout.ecommerce.customer.domain.mapper;

import co.istad.lyhout.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.istad.lyhout.ecommerce.customer.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDomainMapper {

    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "phoneNumber", target = "phoneNumber.number")
    Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand);

}
