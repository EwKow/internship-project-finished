package com.virtuslab.internship.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    /*sample entry:
    POST http://localhost:8080/basket/add
    Content-Type: application/json

    ["Apple", "Orange", "Bread", "Bread", "Bread", "Cheese"]
     */
    @PostMapping(value = "/add",consumes = "application/json")
    @ResponseBody
    public void addProductsToBasked(@RequestBody List<String> names){
        basketService.addProductsToBasket(names);
    }

}
