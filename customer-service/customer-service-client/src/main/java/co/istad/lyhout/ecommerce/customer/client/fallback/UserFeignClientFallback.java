package co.istad.lyhout.ecommerce.customer.client.fallback;

import co.istad.lyhout.ecommerce.customer.client.dto.UserResponse;
import co.istad.lyhout.ecommerce.customer.client.exception.UserServiceUnavailableException;
import co.istad.lyhout.ecommerce.customer.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public UserResponse getUserById(String id) {
        log.info("UserFeignClientFallback getById: {}", id);
        // 1: get cache from Redis

        // 2: requirement process or verify
        throw new UserServiceUnavailableException("Userservice unavailable please try again later !");
    }
}
