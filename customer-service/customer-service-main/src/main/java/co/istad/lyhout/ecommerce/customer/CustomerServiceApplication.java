package co.istad.lyhout.ecommerce.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"co.istad.lyhout.ecommerce.customer.persistence"})
@EnableJpaRepositories(basePackages = "co.istad.lyhout.ecommerce.customer.persistence")
@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
