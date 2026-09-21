package co.istad.lyhout.ecommerce.order.domain.entity;

import co.istad.haklyhout.ecommerce.domain.entity.BaseEntity;
import co.istad.haklyhout.ecommerce.domain.valueobject.Money;
import co.istad.haklyhout.ecommerce.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    private final String name;
    private final Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    private Product(Builder builder) {
        super.setId(builder.id);
        name =  builder.name;
        price = builder.price;
    }

    public static final class Builder {
        public String name;
        private ProductId id;
        private Money price;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
