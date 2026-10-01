package co.istad.lyhout.ecommerce.business.domain.port.output;

import co.istad.lyhout.ecommerce.business.domain.entity.OrderApproval;

public interface OrderApprovalRepository {

    OrderApproval save(OrderApproval orderApproval);

}
