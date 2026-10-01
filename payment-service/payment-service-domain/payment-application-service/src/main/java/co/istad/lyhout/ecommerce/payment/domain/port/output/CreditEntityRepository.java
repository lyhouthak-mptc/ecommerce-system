package co.istad.lyhout.ecommerce.payment.domain.port.output;

import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.lyhout.ecommerce.payment.domain.entity.CreditEntry;

public interface CreditEntityRepository  {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
