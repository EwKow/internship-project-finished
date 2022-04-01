package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries;

        Map<Product, Integer> map = new HashMap<>();
        for (Product p: basket.getProducts()){
            if(!map.containsKey(p)){
                map.put(p,1);
            }else map.replace(p, map.get(p) + 1);
        }
        receiptEntries = map.entrySet()
                .stream()
                .map(entry -> new ReceiptEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Receipt receipt = new Receipt(receiptEntries);

        return this.addingDiscounts(receipt);
    }

    public Receipt addingDiscounts (Receipt receipt){
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        Receipt receipt15 = fifteenPercentDiscount.apply(receipt);
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();

        return tenPercentDiscount.apply(receipt15);
    }
}
