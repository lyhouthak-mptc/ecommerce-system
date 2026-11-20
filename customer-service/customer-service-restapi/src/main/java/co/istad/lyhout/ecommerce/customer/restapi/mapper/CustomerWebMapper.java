package co.istad.lyhout.ecommerce.customer.restapi.mapper;

import co.istad.lyhout.ecommerce.core.PageResponse;
import co.istad.lyhout.ecommerce.customer.domain.dto.*;
import co.istad.lyhout.ecommerce.customer.restapi.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

    CustomerResponse toCustomerResponse(CustomerResult customerResult);

    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);

    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);

    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);

    PageResponse<CustomerResponse> toCustomerResponse(PageResponse<CustomerResult> result);
}
