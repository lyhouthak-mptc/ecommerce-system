package co.istad.lyhout.ecommerce.order.domain.usecase;

import co.istad.haklyhout.ecommerce.domain.valueobject.BusinessId;
import co.istad.haklyhout.ecommerce.domain.valueobject.Money;
import co.istad.haklyhout.ecommerce.domain.valueobject.ProductId;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.lyhout.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.lyhout.ecommerce.order.domain.entity.Business;
import co.istad.lyhout.ecommerce.order.domain.entity.Order;
import co.istad.lyhout.ecommerce.order.domain.entity.Product;
import co.istad.lyhout.ecommerce.order.domain.event.OrderCreatedEvent;
import co.istad.lyhout.ecommerce.order.domain.exception.OrderDomainException;
import co.istad.lyhout.ecommerce.order.domain.mapper.OrderDomainMapper;
import co.istad.lyhout.ecommerce.order.domain.port.output.BusinessRepository;
import co.istad.lyhout.ecommerce.order.domain.port.output.CustomerRepository;
import co.istad.lyhout.ecommerce.order.domain.port.output.OrderRepository;
import co.istad.lyhout.ecommerce.order.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;
    private final OrderDomainService orderDomainService;
    private final OrderDomainMapper orderDomainMapper;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("executing CreateOrderRequest: {}", createOrderCommand);

//        validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException("Could not find customer with ID" + createOrderCommand.customerId()));

//        validate business
        List<Product> products = createOrderCommand.items().stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build())
                .toList();

        Business business = businessRepository.findBusiness(
                        Business.builder()
                                .id(new BusinessId(createOrderCommand.businessId()))
                                .products(products)
                                .build())
                .orElseThrow(() -> new OrderDomainException("Could not get Business ID " + createOrderCommand.businessId()));

        log.info("Found business : {}", business);

//      Invoke order domain logic
        Order order = orderDomainMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, business);
        log.info("Order created : {}", orderCreatedEvent.getOrder().getId());

//      Save order into database
        Order savedOrder = orderRepository.saveOrder(order);
        if (savedOrder == null) {
            throw new OrderDomainException("Could not save order into database");
        }

        return new CreateOrderResult(savedOrder.getId().value());
    }

}

// INSERT, UPDATE, DELETE -> Command -> TRANSACTION
// SELECT -> Query -> TRANSACTION READ ONLY
// CQRS Pattern
