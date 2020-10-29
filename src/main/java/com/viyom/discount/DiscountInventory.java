package com.viyom.discount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscountInventory {
    private static List<Discount> discounts = new ArrayList<>();

    public static void addDiscounts(Discount discount) {
        discounts.add(discount);
    }

    public static List<Discount> getDiscounts(){
        return discounts;
    }
}
