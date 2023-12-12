package com.github.pavelvil.spring.core.aware;

import com.github.pavelvil.spring.core.aware.bean.Menu;
import com.github.pavelvil.spring.core.aware.bean.Waiter;
import com.github.pavelvil.spring.core.aware.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Waiter waiter = context.getBean(Waiter.class);
        waiter.takeOrder("Salad");
        waiter.greetCustomer();

        context.getBean(Menu.class).printMenu();
    }
}
