package com.github.pavelvil.spring.core.event;

import com.github.pavelvil.spring.core.event.bean.Restaurant;
import com.github.pavelvil.spring.core.event.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        context.start();
//        context.stop();

        Restaurant restaurant = context.getBean(Restaurant.class);
        restaurant.placeOrder("Salad");
        restaurant.placeOrder("Pasta");
    }
}
