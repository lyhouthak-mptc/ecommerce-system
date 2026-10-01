package co.istad.lyhout.ecommerce.order.persistence.entity;

import co.istad.haklyhout.ecommerce.domain.valueobject.OrderItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
@IdClass(OrderItemIdEntity.class)
public class OrderItemEntity {
    @Id
    private Integer id;

    private UUID productId;

    private BigDecimal subTotal;
    private String productName;
    private BigDecimal price;

    @Id
    @ManyToOne()
    private OrderEntity order;
}
