package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptGeneratorTest {

    @Test
    void shouldGenerateReceiptForGivenBasketWithoutDiscounts() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");
        var expectedTotalPrice = milk.price().multiply(BigDecimal.valueOf(2)).add(bread.price()).add(apple.price()).setScale(2, RoundingMode.HALF_UP);

        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertAll(
                ()-> assertNotNull(receipt),
                ()-> assertEquals(3, receipt.entries().size()),
                ()-> assertEquals(expectedTotalPrice, receipt.totalPrice()),
                ()-> assertEquals(0, receipt.discounts().size()));
    }

    @Test
    void shouldGenerateReceiptForGivenBasketWithAllDiscounts() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        var sum = cheese.price().multiply(BigDecimal.valueOf(3)).add(bread.price().multiply(BigDecimal.valueOf(3)));
        var expectedTotalPrice = sum.multiply(BigDecimal.valueOf(0.85)).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(0.9)).setScale(2, RoundingMode.HALF_UP);

        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.addProduct(bread);
        cart.addProduct(bread);
        cart.addProduct(bread);


        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertAll(
                ()-> assertNotNull(receipt),
                ()-> assertEquals(2, receipt.entries().size()),
                ()-> assertEquals(expectedTotalPrice, receipt.totalPrice()),
                ()-> assertEquals(2, receipt.discounts().size()));

    }
    @Test
    void shouldGenerateReceiptForGivenBasketOnlyWith15PercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var apple = productDb.getProduct("Apple");
        var bread = productDb.getProduct("Bread");
        var expectedTotalPrice = apple.price().add(bread.price().multiply(BigDecimal.valueOf(3))).multiply(BigDecimal.valueOf(0.85)).setScale(2, RoundingMode.HALF_UP);


        cart.addProduct(apple);
        cart.addProduct(bread);
        cart.addProduct(bread);
        cart.addProduct(bread);


        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertAll(
                ()-> assertNotNull(receipt),
                ()-> assertEquals(2, receipt.entries().size()),
                ()-> assertEquals(expectedTotalPrice, receipt.totalPrice()),
                ()-> assertEquals(1, receipt.discounts().size()));

    }
    @Test
    void shouldGenerateReceiptForGivenBasketOnlyWith10PercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var cheese = productDb.getProduct("Cheese");
        var pork = productDb.getProduct("Pork");
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2)).add(pork.price().multiply(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(0.9)).setScale(2, RoundingMode.HALF_UP);

        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.addProduct(pork);

        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertAll(
                ()-> assertNotNull(receipt),
                ()-> assertEquals(2, receipt.entries().size()),
                ()-> assertEquals(expectedTotalPrice, receipt.totalPrice()),
                ()-> assertEquals(1, receipt.discounts().size()));

    }

}
