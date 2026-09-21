package co.istad.lyhout.ecommerce.order.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_addresses")
public class OrderAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String streetName;
    private String postalCode;
    private String city;

    @OneToOne(mappedBy = "orderAddressEntity")
    private OrderEntity order;

}
