package com.github.pavelvil.spring.core.lifecycle.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

@Component
public class CoffeeShop /*implements InitializingBean, DisposableBean*/ {

    private final ObjectProvider<Coffee> coffeeObjectProvider;

    private final Map<String, Ingredient> ingredients;

    public CoffeeShop(ObjectProvider<Coffee> coffeeObjectProvider, Map<String, Ingredient> ingredients) {
        this.coffeeObjectProvider = coffeeObjectProvider;
        this.ingredients = ingredients;
    }

    @PostConstruct
    public void openShop() {
        System.out.println("Shop is opened");
    }

    public void makeCoffee() {
        System.out.println("Making coffee");
    }

    public void makeCoffee(String type) {
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Making coffee: " + coffee);
    }

    public void makeCoffee(String type, String ingredient) {
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Making coffee with ingredient " + ingredients.get(ingredient) + ". Coffee: " + coffee);
    }

    @PreDestroy
    public void closeShop() {
        System.out.println("Shop is closing");
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("Shop is opened");
//    }

//    @Override
//    public void destroy() throws Exception {
//        System.out.println("Shop is closing");
//    }
}
