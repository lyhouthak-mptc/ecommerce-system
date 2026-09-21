package co.istad.lyhout.ecommerce.order.persistence.adapter;

import co.istad.lyhout.ecommerce.order.domain.entity.Customer;
import co.istad.lyhout.ecommerce.order.domain.port.output.CustomerRepository;
import co.istad.lyhout.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.istad.lyhout.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;
    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(orderPersistenceMapper::customerEntityToCustomer);
    }
}
