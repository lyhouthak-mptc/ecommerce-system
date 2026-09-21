package co.istad.lyhout.ecommerce.order.persistence.repository;

import co.istad.lyhout.ecommerce.order.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID> {
}
