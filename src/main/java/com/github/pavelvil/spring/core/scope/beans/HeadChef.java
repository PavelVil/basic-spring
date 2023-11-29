package com.github.pavelvil.spring.core.scope.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HeadChef {

    private String name;

    public void makeOrder(Waiter waiter) {
        System.out.println("Head chef start cooking. Waiter is: " + waiter);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HeadChef{" +
                "name='" + name + '\'' +
                '}';
    }
}
