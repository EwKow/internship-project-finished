package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FifteenPercentDiscountTest {

    @Test
    void shouldApply15PercentDiscountWhenAtLeast3ProductsAreGrains() {
        // Given
        var productDb = new ProductDb();
        var cereals = productDb.getProduct("Cereals");
        var bread = productDb.getProduct("Bread");
        var milk = productDb.getProduct("Milk");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(bread, 1));
        receiptEntries.add(new ReceiptEntry(milk, 1));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = cereals.price().multiply(BigDecimal.valueOf(2)).add(bread.price()).add(milk.price()).multiply(BigDecimal.valueOf(0.85)).setScale(2, RoundingMode.HALF_UP);

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15PercentDiscountWhenLessThan3ProductsAreGrains() {
        // Given
        var productDb = new ProductDb();
        var cereals = productDb.getProduct("Cereals");
        var milk = productDb.getProduct("Milk");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cereals, 2));
        receiptEntries.add(new ReceiptEntry(milk, 1));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = cereals.price().multiply(BigDecimal.valueOf(2)).add(milk.price()).setScale(2, RoundingMode.HALF_UP);

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}
