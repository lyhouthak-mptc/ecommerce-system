package co.istad.lyhout.ecommerce.payment.domain.port.output;

import co.istad.lyhout.ecommerce.payment.domain.entity.CreditHistory;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);
}
