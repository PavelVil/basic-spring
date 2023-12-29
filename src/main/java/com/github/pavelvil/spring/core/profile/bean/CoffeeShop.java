package com.github.pavelvil.spring.core.profile.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShop {

    private final Coffee coffee;

    @Value("${app.shop-name}")
    private String shopName;

    public CoffeeShop(Coffee coffee) {
        this.coffee = coffee;
    }

    public void orderCoffee() {
        System.out.println("Order: " + coffee.brew());
    }

    public void printShopName() {
        System.out.println(shopName);
    }
}
