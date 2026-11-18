package co.istad.lyhout.ecommerce.customer.restapi.controller;

import co.istad.lyhout.ecommerce.customer.client.dto.UserResponse;
import co.istad.lyhout.ecommerce.customer.client.feign.UserFeignClient;
import co.istad.lyhout.ecommerce.customer.domain.usecase.GetCustomerByUserIdUseCase;
import co.istad.lyhout.ecommerce.customer.restapi.dto.CustomerResponse;
import co.istad.lyhout.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;
    private final CustomerWebMapper customerWebMapper;

    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId) {
        return userFeignClient.getUserById(userId);
    }

    @GetMapping("/user/{userId}")
    public CustomerResponse getCustomerByUserId(@PathVariable String userId) {
        return customerWebMapper.customerResultToCustomerResponse(
                getCustomerByUserIdUseCase.execute(userId));
    }

    @GetMapping
    public CustomerResponse getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return null;
    }
}
