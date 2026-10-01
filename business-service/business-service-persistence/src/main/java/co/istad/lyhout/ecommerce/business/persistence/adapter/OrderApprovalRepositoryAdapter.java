package co.istad.lyhout.ecommerce.business.persistence.adapter;

import co.istad.lyhout.ecommerce.business.domain.entity.OrderApproval;
import co.istad.lyhout.ecommerce.business.domain.port.output.OrderApprovalRepository;
import co.istad.lyhout.ecommerce.business.persistence.entity.OrderApprovalEntity;
import co.istad.lyhout.ecommerce.business.persistence.mapper.OrderApprovalPersistenceMapper;
import co.istad.lyhout.ecommerce.business.persistence.repository.OrderApprovalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity orderApprovalEntity = orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);

        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(orderApprovalJpaRepository.save(orderApprovalEntity));
    }
}
