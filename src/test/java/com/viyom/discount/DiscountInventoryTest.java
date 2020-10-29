package com.viyom.discount;

import com.viyom.product.ProductInventory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountInventoryTest {

    ProductAtLessPriceDiscount actualProductAtLessPriceDiscount;
    void setUp() {

    }

    @Test
    void addDiscounts() {
        LocalDateTime startdate1 = LocalDateTime.now().minusDays(1);
        actualProductAtLessPriceDiscount= new ProductAtLessPriceDiscount(startdate1, startdate1.plusDays(7), ProductInventory.getProduct("soup"),
                2,ProductInventory.getProduct("bread"), 0.5F);
        DiscountInventory.addDiscounts(actualProductAtLessPriceDiscount);
        List<Discount> expectedProductAtLessPriceDiscount=DiscountInventory.getDiscounts();
        assertEquals(actualProductAtLessPriceDiscount,expectedProductAtLessPriceDiscount.get(0));
    }

    @Test
    void getDiscounts() {
        LocalDateTime startdate1 = LocalDateTime.now().minusDays(1);
        actualProductAtLessPriceDiscount= new ProductAtLessPriceDiscount(startdate1, startdate1.plusDays(7), ProductInventory.getProduct("soup"),
                2,ProductInventory.getProduct("bread"), 0.5F);
        DiscountInventory.addDiscounts(actualProductAtLessPriceDiscount);
        List<Discount> expectedProductAtLessPriceDiscount=DiscountInventory.getDiscounts();
        assertEquals(actualProductAtLessPriceDiscount,expectedProductAtLessPriceDiscount.get(1));
    }
}