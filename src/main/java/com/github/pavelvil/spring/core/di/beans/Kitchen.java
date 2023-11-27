package com.github.pavelvil.spring.core.di.beans;

import org.springframework.stereotype.Component;

@Component
public class Kitchen {
    public void cook(String order) {
        System.out.println("Kitchen start cooking: " + order);
    }
}
