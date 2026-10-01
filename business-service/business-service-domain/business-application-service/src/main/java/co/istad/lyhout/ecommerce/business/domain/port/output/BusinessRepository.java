package co.istad.lyhout.ecommerce.business.domain.port.output;

import co.istad.lyhout.ecommerce.business.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusinessInformation(Business business);

}
