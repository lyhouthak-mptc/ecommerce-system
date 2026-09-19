package co.istad.lyhout.ecommerce.order.restapi.mapper;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
//    source = OrderCreateRequest
//    target = CreateOrderCommand
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(CreateOrderResult createOrderResult);
}
