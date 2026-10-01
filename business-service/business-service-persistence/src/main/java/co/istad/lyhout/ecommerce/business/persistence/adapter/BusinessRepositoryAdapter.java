package co.istad.lyhout.ecommerce.business.persistence.adapter;

import co.istad.lyhout.ecommerce.business.domain.entity.Business;
import co.istad.lyhout.ecommerce.business.domain.port.output.BusinessRepository;
import co.istad.lyhout.ecommerce.business.persistence.entity.BusinessEntity;
import co.istad.lyhout.ecommerce.business.persistence.mapper.BusinessPersistenceMapper;
import co.istad.lyhout.ecommerce.business.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findBusinessInformation(Business business) {
        List<UUID> businessProducts = businessPersistenceMapper.businessToBusinessProducts(business);

        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessIdAndProductIdIn(
                business.getId().value(),
                businessProducts
        );

        return Optional.of(businessPersistenceMapper.businessEntityToBusiness(businessEntities));
    }
}
