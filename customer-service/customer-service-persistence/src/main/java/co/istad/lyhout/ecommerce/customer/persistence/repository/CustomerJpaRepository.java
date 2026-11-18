package co.istad.lyhout.ecommerce.customer.persistence.repository;

import co.istad.lyhout.ecommerce.customer.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByUserId(String userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
