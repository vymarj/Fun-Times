package com.viyom.discount;

import com.viyom.basket.Basket;
import com.viyom.product.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class ProductAtLessPriceDiscount implements Discount {

    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final Product productAtLessPrice;
    private final Float atPrice;
    private final Product productUnderOffer;
    private final Integer quantityUnderOffer;

    public ProductAtLessPriceDiscount(LocalDateTime validFrom, LocalDateTime validTo, Product productUnderOffer,
                                      Integer quantityUnderOffer, Product productAtLessPrice, Float atPrice) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.productUnderOffer = productUnderOffer;
        this.quantityUnderOffer = quantityUnderOffer;
        this.productAtLessPrice = productAtLessPrice;
        this.atPrice = atPrice;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        return basket.getBasketItems().entrySet()
                .stream()
                .anyMatch(e -> (e.getKey().getName().equals(productUnderOffer.getName()) && e.getValue() >= 2))
                &&
                basket.getDateBought().isAfter(validFrom) && basket.getDateBought().isBefore(validTo);
    }

    @Override
    public void getOffer(Basket basket) {
        Map<Product, Integer> basketItems = basket.getBasketItems();
        Map<Product, Float> basketItemCost = basket.getBasketItemPrice();
        Optional<Product> productOptional = basketItems.keySet().stream()
                .filter(e -> e.getName().equals(productUnderOffer.getName()))
                .findFirst();
        Optional<Product> productAtLessPriceOptional = basketItems.keySet().stream()
                .filter(e -> e.getName().equals(productAtLessPrice.getName()))
                .findFirst();

        Product productInOffer = productOptional.get();
        int quantity = basketItems.get(productInOffer);
        int quantityAtLessPrice = quantity % quantityUnderOffer;
        int productQuantity = 0;

        Product productAtLessPriceBO = null;
        if (productAtLessPriceOptional.isPresent()) {
            productAtLessPriceBO = productAtLessPriceOptional.get();
        }

        if (basketItems.containsKey(productAtLessPriceBO)) {
            productQuantity = basketItems.get(productAtLessPriceBO);
            if (productQuantity >= quantityAtLessPrice) {
                float priceOfProduct = (quantityAtLessPrice * productAtLessPriceBO.getCost() * atPrice)
                        + ((productQuantity - quantityAtLessPrice) * productAtLessPriceBO.getCost());
                basketItemCost.put(productAtLessPriceBO, priceOfProduct);
            }
        }
    }
}
