package co.istad.lyhout.ecommerce.order.domain.dto;

import co.istad.haklyhout.ecommerce.domain.valueobject.BusinessId;
import co.istad.haklyhout.ecommerce.domain.valueobject.CustomerId;
import co.istad.haklyhout.ecommerce.domain.valueobject.Money;
import co.istad.haklyhout.ecommerce.domain.valueobject.StreetAddress;

public record CreateOrderRequest(

        CustomerId customerId,
        BusinessId businessId,
        StreetAddress streetAddress,
        Money price

) {



}
