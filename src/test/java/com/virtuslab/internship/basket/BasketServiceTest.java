package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketServiceTest {

    @Test
    void baskedShouldBeEmptyAfterClearBasket() {
        //given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");

        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        //when
        cart.deleteProducts();

        //then
        assertEquals(0, cart.getProducts().size());
    }
}