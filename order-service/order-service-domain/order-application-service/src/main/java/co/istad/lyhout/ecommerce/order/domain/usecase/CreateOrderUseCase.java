package co.istad.lyhout.ecommerce.order.domain.usecase;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("executing CreateOrderRequest: {}", createOrderCommand);
        return new CreateOrderResult(UUID.randomUUID());
    }

}

// INSERT, UPDATE, DELETE -> Command -> TRANSACTION
// SELECT -> Query -> TRANSACTION READ ONLY
// CQRS Pattern
