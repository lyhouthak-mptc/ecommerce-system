package co.istad.lyhout.ecommerce.order.domain.port.output;

import co.istad.lyhout.ecommerce.order.domain.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    Optional<Business> findBusiness(UUID businessId);
}
