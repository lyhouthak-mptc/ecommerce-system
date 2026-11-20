package co.istad.lyhout.ecommerce.customer.restapi.controller;

import co.istad.lyhout.ecommerce.core.PageResponse;
import co.istad.lyhout.ecommerce.customer.client.dto.UserResponse;
import co.istad.lyhout.ecommerce.customer.client.feign.UserFeignClient;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.dto.GetCustomersQuery;
import co.istad.lyhout.ecommerce.customer.domain.usecase.GetAllCustomersUseCase;
import co.istad.lyhout.ecommerce.customer.domain.usecase.GetCustomerByIdUseCase;
import co.istad.lyhout.ecommerce.customer.restapi.dto.CustomerResponse;
import co.istad.lyhout.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final CustomerWebMapper customerWebMapper;


    @GetMapping("/{id}")
    public CustomerResponse getByIdWithCache(@PathVariable UUID id) {
        CustomerResult result = getCustomerByIdUseCase.execute(id);
        return customerWebMapper.toCustomerResponse(result);
    }


    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        PageResponse<CustomerResult> result = getAllCustomersUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }


    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId) {
        return userFeignClient.getById(userId);
    }

}
