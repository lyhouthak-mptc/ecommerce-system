package co.istad.lyhout.ecommerce.domain.port.input;

import co.istad.lyhout.ecommerce.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {

    void execute(CreateOrderRequest createOrderRequest);

}
