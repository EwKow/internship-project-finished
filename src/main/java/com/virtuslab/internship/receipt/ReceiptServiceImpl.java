package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.basket.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    public ReceiptServiceImpl(BasketService basketService) {
        this.basketService = basketService;
    }

    BasketService basketService;

    @Override
    public Receipt generateReceipt(Basket basket) {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        Receipt receipt = receiptGenerator.generate(basket);
        basketService.clearBasket(basket);
        return receipt;
    }

    @Override
    public Receipt getReceipt() {
        return this.generateReceipt(basketService.getBasket());
    }


}
