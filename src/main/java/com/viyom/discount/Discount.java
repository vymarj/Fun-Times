package com.viyom.discount;

import com.viyom.basket.Basket;

public interface Discount {

    boolean isApplicable(Basket basket);
    void getOffer(Basket basket);
}
