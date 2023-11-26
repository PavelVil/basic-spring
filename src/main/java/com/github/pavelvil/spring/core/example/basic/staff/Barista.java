package com.github.pavelvil.spring.core.example.basic.staff;

import org.springframework.stereotype.Component;

@Component
public class Barista {
    public void makeCoffee() {
        System.out.println("Barista making coffee...");
    }
}
