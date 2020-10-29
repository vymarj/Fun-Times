package com.viyom.discount;

import com.viyom.basket.Basket;
import com.viyom.product.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class PercentageDiscount implements Discount {

    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final int percentage;
    private final Product productAtDiscount;


    public PercentageDiscount(LocalDateTime validFrom, LocalDateTime validTo, int percentage, Product productAtDiscount) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.percentage = percentage;
        this.productAtDiscount = productAtDiscount;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        return (
                basket.getBasketItems().entrySet().stream().anyMatch(e -> e.getKey().getName().equals(productAtDiscount.getName()))
                        && basket.getDateBought().isAfter(validFrom)
                        && basket.getDateBought().isBefore(validTo));
    }

    @Override
    public void getOffer(Basket basket) {
        Map<Product, Integer> basketItems = basket.getBasketItems();
        Optional<Product> productOptional = basketItems.keySet().stream().filter(e -> e.getName().equals(productAtDiscount.getName())).findFirst();
        Product apple = productOptional.get();
        Float discountPerApple = apple.getCost() * percentage / 100;
        Integer quantity = basketItems.get(apple);
        Float totalPrice = (apple.getCost() - discountPerApple) * quantity;
        basket.getBasketItemPrice().put(apple, totalPrice);
    }
}
