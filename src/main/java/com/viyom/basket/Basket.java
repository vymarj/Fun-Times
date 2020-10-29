package com.viyom.basket;

import com.viyom.product.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private Map<Product,Integer> basketItems;
    private Map<Product,Float> basketItemPrice;
    private LocalDateTime dateBought;
    private float totalPrice;

    public static Basket createBasket(int date){
        Basket basket=new Basket();
        basket.dateBought=LocalDateTime.now().plusDays(date);
        return basket;
    }

    private Basket() {
        this.basketItems = new HashMap<>();
        basketItemPrice=new HashMap<>();
        this.totalPrice = 0.0F;
    }

    public LocalDateTime getDateBought() {
        return dateBought;
    }

    public Map<Product, Integer> getBasketItems() {
        return basketItems;
    }

    public Map<Product, Float> getBasketItemPrice() {
        return basketItemPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void addItem(Product product, int quantity){
        basketItems.put(product,quantity);
    }

    public void calculateSubTotal(){

        for(Map.Entry<Product,Integer> entry:basketItems.entrySet()){
            Product product=(Product)entry.getKey();
            Integer quantity= entry.getValue();
            Float totalPriceOfItem=product.getCost() * quantity;
            basketItemPrice.put(product,totalPriceOfItem);
        }
    }


    public Float calculateTotal(){
        for(Map.Entry<Product,Float> entry:basketItemPrice.entrySet()){
            totalPrice += entry.getValue();
        }
        return totalPrice;
    }

    public void addAllItem(Map<Product,Integer> map) {
        basketItems.putAll(map);
    }


}
