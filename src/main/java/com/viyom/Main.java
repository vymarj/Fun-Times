package com.viyom;

import com.viyom.discount.DiscountInventory;
import com.viyom.discount.PercentageDiscount;
import com.viyom.discount.ProductAtLessPriceDiscount;
import com.viyom.menu.MainMenuHandler;
import com.viyom.product.Product;
import com.viyom.product.ProductInventory;

import java.time.LocalDateTime;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ProductInventory.addProductToInventory(Product.createProduct("soup","tin",0.65F));
        ProductInventory.addProductToInventory(Product.createProduct("bread","loaf",0.80F));
        ProductInventory.addProductToInventory(Product.createProduct("milk","bottle",1.30F));
        ProductInventory.addProductToInventory(Product.createProduct("apples","single",0.10F));

        LocalDateTime startdate = LocalDateTime.now().plusDays(3);
        DiscountInventory.addDiscounts(new PercentageDiscount(startdate, startdate.plusMonths(1), 10,ProductInventory.getProduct("apples")));

        LocalDateTime startdate1 = LocalDateTime.now().minusDays(1);
        DiscountInventory.addDiscounts(new ProductAtLessPriceDiscount(startdate1, startdate1.plusDays(7), ProductInventory.getProduct("soup"),
                2,ProductInventory.getProduct("bread"), 0.5F));

        int date=MainMenuHandler.getShoppingDate();
        Map<String, Integer> itemQuantityMap = MainMenuHandler.addProducts();
        ShoppingService shoppingService = new ShoppingService(itemQuantityMap,date);
        System.out.println(shoppingService.processCheckout());
    }
}
