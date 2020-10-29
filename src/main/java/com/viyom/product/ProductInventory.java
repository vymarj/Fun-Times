package com.viyom.product;

import java.util.HashMap;
import java.util.Map;

public class ProductInventory {
    private static final Map<String, Product> productInventory = new HashMap<>();

    public static void addProductToInventory(Product product) {
        productInventory.put(product.getName(), product);
    }

    public static Product getProduct(String name) {
        return productInventory.get(name);
    }
}
