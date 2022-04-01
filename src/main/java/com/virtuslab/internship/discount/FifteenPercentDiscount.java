package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import java.math.BigDecimal;

import static com.virtuslab.internship.product.Product.Type.GRAINS;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var TotalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, TotalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        return receipt.entries().stream().filter(e -> e.product().type().equals(GRAINS)).reduce(0, (quantity, e) -> quantity + e.quantity(), Integer::sum)
                >= 3;
    }
}
