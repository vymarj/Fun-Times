package com.viyom;

import com.viyom.basket.Basket;
import com.viyom.discount.Discount;
import com.viyom.discount.DiscountInventory;
import com.viyom.product.Product;
import com.viyom.product.ProductInventory;

import java.util.HashMap;
import java.util.Map;

public class ShoppingService {

    private final Basket basket;
    private Map<String, Product> productMap;

    public ShoppingService(Map<String, Integer> itemNoQuantityMap, int date) {
        this.basket = Basket.createBasket(date);
        Map<Product, Integer> productMap = new HashMap<>();
        for (String item : itemNoQuantityMap.keySet()) {
            int quantity = itemNoQuantityMap.get(item);
            productMap.put(ProductInventory.getProduct(item), quantity);
        }
        basket.addAllItem(productMap);
    }

    public Float processCheckout() {
        basket.calculateSubTotal();
        for (Discount discount : DiscountInventory.getDiscounts()) {
            if (discount.isApplicable(basket))
                discount.getOffer(basket);
        }
        return basket.calculateTotal();
    }

}
