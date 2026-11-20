package co.istad.lyhout.ecommerce.payment.domain.ports.output.repository;

import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.lyhout.ecommerce.payment.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
