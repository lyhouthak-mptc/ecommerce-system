package co.istad.haklyhout.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(
        BigDecimal amount
) {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    //    validate money amount is greater than
    public boolean isGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

//    validate amount is greater than input
    public boolean isGreaterThan(Money money) {
        return amount.compareTo(money.amount) > 0;
    }

//  input maount
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

//    withdraw money
    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

//    mutliply money
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

//   set scale amoiunt
    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
