package co.istad.lyhout.ecommerce.customer.client.feign;

import co.istad.lyhout.ecommerce.customer.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable("id") String id);
}
