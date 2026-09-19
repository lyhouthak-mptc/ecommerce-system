package co.istad.lyhout.ecommerce.order.domain.port.input;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {

    void execute(CreateOrderCommand createOrderCommand);

}
