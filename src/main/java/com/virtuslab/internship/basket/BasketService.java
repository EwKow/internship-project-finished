package com.virtuslab.internship.basket;

import java.util.List;

public interface BasketService {

    void addProductsToBasket(List<String> products);
    Basket getBasket();
    void clearBasket(Basket basket);
}
