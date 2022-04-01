package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;

public interface ReceiptService {

    Receipt generateReceipt(Basket basket);
    Receipt getReceipt();
}
