package com.github.pavelvil.spring.core.spel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class Shop {

    @Value("#{productList.getProducts()}")
    private List<ProductList.Product> allProducts;

    @Value("#{productList.getProducts().?[getFit()]}")
    private List<ProductList.Product> suitableProducts;

    @Value("#{productList.getProducts().?[getCount() < 10]}")
    private List<ProductList.Product> endingProducts;

    @Value("#{productList.getProducts()[2]}")
    private ProductList.Product vegetables;

    @Value("#{T(java.time.LocalTime).of(9, 0)}")
    private LocalTime openTime;

    public void printAllProducts() {
        System.out.println("All products: " + allProducts);
    }

    public void printSuitableProducts() {
        System.out.println("Suitable products: " + suitableProducts);
    }

    public void printEndingProducts() {
        System.out.println("Ending products: " + endingProducts);
    }

    public void printVegetablesInfo() {
        System.out.println("Vegetables info: " + vegetables);
    }

    public void printOpenTime() {
        System.out.println("Open time: " + openTime);
    }

    public LocalTime getOpenTime() {
        return openTime;
    }
}
