package com.viyom.product;


import java.util.Objects;

public class Product {

    private final String name;

    private final String unit;

    private final float cost;

    private Product(String name, String unit, float cost) {
        this.name = name;
        this.unit = unit;
        this.cost = cost;
    }

    public static Product createProduct(String name, String unit, Float cost) {
        if (name == null || unit == null || cost == null)
            return null;
        else
            return new Product(name, unit, cost);
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public float getCost() {
        return cost;
    }
}
