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

    @PostMapping(value = "/add",consumes = "application/json")
    @ResponseBody
    public void addProductsToBasked(@RequestBody List<String> names){
        basketService.addProductsToBasket(names);
    }

}
