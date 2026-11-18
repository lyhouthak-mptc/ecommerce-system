package co.istad.lyhout.ecommerce.client.ecommerce.order.domain.port.output;

import co.istad.lyhout.ecommerce.order.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusiness(Business business);

}
