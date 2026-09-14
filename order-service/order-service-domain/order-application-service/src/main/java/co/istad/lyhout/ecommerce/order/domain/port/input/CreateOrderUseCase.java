package co.istad.lyhout.ecommerce.order.domain.port.input;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {

    void execute(CreateOrderRequest createOrderRequest);

}
