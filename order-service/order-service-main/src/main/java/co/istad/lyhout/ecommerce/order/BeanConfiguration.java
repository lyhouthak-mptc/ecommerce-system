package co.istad.lyhout.ecommerce.order;

import co.istad.lyhout.ecommerce.order.domain.service.OrderDomainService;
import co.istad.lyhout.ecommerce.order.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// How to configure bean
// 1. Annotation based
// 2. Java based (method)
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
