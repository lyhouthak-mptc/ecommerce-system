package co.istad.lyhout.ecommerce.order.restapi.controller;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.lyhout.ecommerce.order.domain.usecase.CreateOrderUseCase;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateResponse;
import co.istad.lyhout.ecommerce.order.restapi.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {
//    Declare required dependencies
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderWebMapper orderWebMapper;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
//      Mapping logic
        CreateOrderCommand createOrderCommand = orderWebMapper.orderCreateRequestToCreateOrderCommand(orderCreateRequest);
//      Call mapping logic
        CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);

        return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
    }
}
