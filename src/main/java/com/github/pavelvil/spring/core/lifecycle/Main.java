package com.github.pavelvil.spring.core.lifecycle;

import com.github.pavelvil.spring.core.lifecycle.bean.CoffeeShop;
import com.github.pavelvil.spring.core.lifecycle.config.LifecycleConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);

//        createCoffeeShop(context);
//        createCoffeeShop(context, "Latte");
        createCoffeeShop(context, "Latte", "sugar");
        createCoffeeShop(context, "Latte", "milk");
//        createCoffeeShop(context, "Latte", "non-existed");

        context.close();
        System.out.println("Context is closed");
    }

    private static void createCoffeeShop(ApplicationContext context) {
        CoffeeShop shop = context.getBean(CoffeeShop.class);
        shop.makeCoffee();
    }

    private static void createCoffeeShop(ApplicationContext context, String coffeeType) {
        CoffeeShop shop = context.getBean(CoffeeShop.class);
        shop.makeCoffee(coffeeType);
    }

    private static void createCoffeeShop(ApplicationContext context, String coffeeType, String ingredient) {
        CoffeeShop shop = context.getBean(CoffeeShop.class);
        shop.makeCoffee(coffeeType, ingredient);
    }
}
