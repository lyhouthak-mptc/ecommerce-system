package co.istad.lyhout.ecommerce.order.persistence.adapter;

import co.istad.lyhout.ecommerce.order.domain.entity.Business;
import co.istad.lyhout.ecommerce.order.domain.port.output.BusinessRepository;
import co.istad.lyhout.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.istad.lyhout.ecommerce.order.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {
    private final BusinessJpaRepository businessJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Optional<Business> findBusiness(UUID businessId) {
        return businessJpaRepository.findById(businessId).map(orderPersistenceMapper::businessEntityToBusiness);
    }
}
