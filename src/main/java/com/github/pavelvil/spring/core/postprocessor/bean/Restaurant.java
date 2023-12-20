package com.github.pavelvil.spring.core.postprocessor.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class Restaurant {

    private final LocalTime openTime;

    private final List<Food> menu;

    @Autowired
    public Restaurant(LocalTime openTime, List<Food> menu) {
        this.openTime = openTime;
        this.menu = menu;
    }

    public void printInfo() {
        System.out.println("Open time is " + openTime);
        System.out.println("Restaurant menu: " + menu);
    }
}
