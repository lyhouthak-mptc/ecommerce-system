package co.istad.lyhout.ecommerce.order.restapi.mapper;

import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.istad.lyhout.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

    // Source = OrderCreateRequest
    // Target = CreateOrderCommand
    @Mapping(source = "orderAddress", target = "deliveryAddress")
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(
            OrderCreateRequest orderCreateRequest
    );

    OrderCreateResponse createOrderResultToOrderCreateResponse(
            CreateOrderResult createOrderResult
    );

}
