package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService{

    ProductDb productDb = new ProductDb();
    Basket basket = new Basket();


    @Override
    public void addProductsToBasket(List<String> productsNames) {
        List<Product> productsList;
        productsList = productsNames.stream().map(e -> productDb.getProduct(e)).collect(Collectors.toList());
        productsList.forEach(prod -> basket.addProduct(prod));
    }

    @Override
    public Basket getBasket() {
        return this.basket;
    }


    @Override
    public void clearBasket(Basket basket) {
        basket.deleteProducts();
    }
}
