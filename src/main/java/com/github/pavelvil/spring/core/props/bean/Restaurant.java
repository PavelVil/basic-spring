package com.github.pavelvil.spring.core.props.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Restaurant {

    @Value("${restaurant.name}")
    private String name;

    @Value("${restaurant.capacity}")
    private int capacity;

    @Value("#{'${restaurant.menu.itemsString}'.split(',')}")
    private List<String> menu;

    @Value("${restaurant.description}")
    private String description;

    public void printMainInfo() {
        System.out.println("Name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Description: " + description);
    }

    public void printMenu() {
        System.out.println("Menu: " + menu);
    }

}
