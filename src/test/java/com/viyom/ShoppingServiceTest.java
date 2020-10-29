package com.viyom;

import com.viyom.discount.DiscountInventory;
import com.viyom.discount.PercentageDiscount;
import com.viyom.discount.ProductAtLessPriceDiscount;
import com.viyom.product.Product;
import com.viyom.product.ProductInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingServiceTest {

    ShoppingService shoppingService;

    @BeforeEach
    void setUp() {
        ProductInventory.addProductToInventory(Product.createProduct("soup","tin",0.65F));
        ProductInventory.addProductToInventory(Product.createProduct("bread","loaf",0.80F));
        ProductInventory.addProductToInventory(Product.createProduct("milk","bottle",1.30F));
        ProductInventory.addProductToInventory(Product.createProduct("apples","single",0.10F));

        LocalDateTime startdate = LocalDateTime.now().plusDays(3);
        DiscountInventory.addDiscounts(new PercentageDiscount(startdate, startdate.plusMonths(1), 10, ProductInventory.getProduct("apples")));

        LocalDateTime startdate1 = LocalDateTime.now().minusDays(1);
        DiscountInventory.addDiscounts(new ProductAtLessPriceDiscount(startdate1, startdate1.plusDays(7), ProductInventory.getProduct("soup"),
                2,ProductInventory.getProduct("bread"), 0.5F));
    }

    @ParameterizedTest
    @CsvSource({
            "soup, 3, bread, 2, 0, 3.15",
            "apples, 6, milk, 1, 0, 1.90",
            "apples, 6, milk, 1, 5, 1.84"
    })
    void processCheckout_withTwoProduct(ArgumentsAccessor arguments) {
        Map<String, Integer> itemQuantityMap=new HashMap();
        itemQuantityMap.put(arguments.getString(0), arguments.getInteger(1));
        itemQuantityMap.put(arguments.getString(2), arguments.getInteger(3));
        shoppingService = new ShoppingService(itemQuantityMap,arguments.getInteger(4));
        Float expectedTotal=arguments.getFloat(5);
        Float actualTotal=shoppingService.processCheckout();

        assertEquals(expectedTotal,Math.round(actualTotal * 100.0F) / 100.0F);
    }

    @ParameterizedTest
    @CsvSource({
            "apples, 3, soup, 2, bread, 1, 5, 1.97"
    })
    void processCheckout_withThreeProduct(ArgumentsAccessor arguments) {
        Map<String, Integer> itemQuantityMap=new HashMap();
        itemQuantityMap.put(arguments.getString(0), arguments.getInteger(1));
        itemQuantityMap.put(arguments.getString(2), arguments.getInteger(3));
        itemQuantityMap.put(arguments.getString(4), arguments.getInteger(5));
        shoppingService = new ShoppingService(itemQuantityMap,arguments.getInteger(6));
        Float expectedTotal=arguments.getFloat(7);
        Float actualTotal=shoppingService.processCheckout();

        assertEquals(expectedTotal,Math.round(actualTotal * 100.0F) / 100.0F);
    }
}