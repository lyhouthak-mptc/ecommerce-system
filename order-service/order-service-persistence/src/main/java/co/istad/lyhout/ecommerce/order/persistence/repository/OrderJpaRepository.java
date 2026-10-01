package co.istad.lyhout.ecommerce.order.persistence.repository;

import co.istad.lyhout.ecommerce.order.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Benefit of using Spring Data JPA => Relational DB
// 1. Boilerplate jpa
// 2. abstraction repository
// 3. Derived query Method (auto generate sql)
// 4. object relational mapping ORM hibernate
// 5. Specification dynamic query

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
