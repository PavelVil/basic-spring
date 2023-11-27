package com.github.pavelvil.spring.core.di;

import com.github.pavelvil.spring.core.di.beans.Customer;
import com.github.pavelvil.spring.core.di.config.CafeConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CageApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CafeConfiguration.class);

        Customer customer = context.getBean("customer", Customer.class);
        customer.makeOrder("Salad and Pasta");
    }
}
