package co.istad.lyhout.ecommerce.order.persistence.entity;

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
@Table(name = "products")
public class ProductEntity {

    @Id
    private UUID id;

    private String name;

    private BigDecimal price;

    @ManyToOne()
    private BusinessEntity business;
}
