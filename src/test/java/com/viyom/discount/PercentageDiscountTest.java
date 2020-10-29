package com.viyom.discount;

import com.viyom.basket.Basket;
import com.viyom.product.Product;
import com.viyom.product.ProductInventory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PercentageDiscountTest {

    private static PercentageDiscount percentageDiscount;

    private static Basket basket;
    @BeforeAll
    static void before() {
        ProductInventory.addProductToInventory(Product.createProduct("soup","tin",0.65F));
        ProductInventory.addProductToInventory(Product.createProduct("bread","loaf",0.80F));
        ProductInventory.addProductToInventory(Product.createProduct("milk","bottle",1.30F));
        ProductInventory.addProductToInventory(Product.createProduct("apples","single",0.10F));
        LocalDateTime startdate = LocalDateTime.now().plusDays(3);
        percentageDiscount=new PercentageDiscount(startdate, startdate.plusMonths(1), 10,ProductInventory.getProduct("apples"));
        Map<Product, Integer> productMap = new HashMap<>();

        productMap.put(ProductInventory.getProduct("apples"), 6);
        productMap.put(ProductInventory.getProduct("milk"), 1);

        basket = Basket.createBasket(5);
        basket.addAllItem(productMap);

    }

    @Test
    void isApplicable() {
        assertTrue(percentageDiscount.isApplicable(basket));
    }



    @Test
    void getOffer() {
        percentageDiscount.getOffer(basket);
        basket.getBasketItemPrice().get(ProductInventory.getProduct("apples"));
        assertEquals(0.54F, basket.getBasketItemPrice().get(ProductInventory.getProduct("apples")));
    }
}