package co.istad.lyhout.ecommerce.order;

import co.istad.lyhout.ecommerce.order.domain.service.OrderDomainService;
import co.istad.lyhout.ecommerce.order.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// How to configure Bean
//1-Annotation bean
//2-Java based configuration (method)
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
