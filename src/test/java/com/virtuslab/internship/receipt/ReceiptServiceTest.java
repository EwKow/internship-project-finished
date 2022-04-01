package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.basket.BasketService;
import com.virtuslab.internship.basket.BasketServiceImpl;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptServiceTest {

    BasketService basketService = new BasketServiceImpl();

    @Test
    void shouldClearBasketAfterGeneratingReceipt() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");

        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);
        basketService.clearBasket(cart);

        // Then
        assertAll(
                ()-> assertNotNull(receipt),
                ()-> assertEquals(3, receipt.entries().size()),
                ()-> assertEquals(0, cart.getProducts().size()));
    }

}