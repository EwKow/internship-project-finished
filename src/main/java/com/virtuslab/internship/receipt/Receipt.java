package com.virtuslab.internship.receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public record Receipt(
        List<ReceiptEntry> entries,
        List<String> discounts,
        BigDecimal totalPrice) {

    public Receipt(List<ReceiptEntry> entries, List<String> discounts, BigDecimal totalPrice) {
        this.entries = entries;
        this.discounts = discounts;
        this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new ArrayList<>(),
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP)
        );
    }

}
