package com.viyom.discount;

import com.viyom.basket.Basket;
import com.viyom.product.Product;
import com.viyom.product.ProductInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductAtLessPriceDiscountTest {

    private static ProductAtLessPriceDiscount productAtLessPriceDiscount;

    private static Basket basket;
    @BeforeEach
    void setUp() {

        ProductInventory.addProductToInventory(Product.createProduct("soup","tin",0.65F));
        ProductInventory.addProductToInventory(Product.createProduct("bread","loaf",0.80F));
        ProductInventory.addProductToInventory(Product.createProduct("milk","bottle",1.30F));
        ProductInventory.addProductToInventory(Product.createProduct("apples","single",0.10F));
        LocalDateTime startdate1 = LocalDateTime.now().minusDays(1);
        productAtLessPriceDiscount=new ProductAtLessPriceDiscount(startdate1, startdate1.plusDays(7), ProductInventory.getProduct("soup"),
                2,ProductInventory.getProduct("bread"), 0.5F);
        Map<Product, Integer> productMap = new HashMap<>();
        productMap.put(ProductInventory.getProduct("soup"), 3);
        productMap.put(ProductInventory.getProduct("bread"), 2);

        basket=Basket.createBasket(0);
        basket.addAllItem(productMap);


    }

    @Test
    void isApplicable() {
        assertTrue(productAtLessPriceDiscount.isApplicable(basket));
    }

    @Test
    void getOffer() {
        productAtLessPriceDiscount.getOffer(basket);
        assertEquals(1.2F,basket.getBasketItemPrice().get(ProductInventory.getProduct("bread")));
    }
}