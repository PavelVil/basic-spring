package com.github.pavelvil.spring.core.scope.beans;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderProcessor {

    private final ObjectProvider<Waiter> waiterObjectProvider;

    private final HeadChef headChef;

    public OrderProcessor(ObjectProvider<Waiter> waiterObjectProvider, HeadChef headChef) {
        this.waiterObjectProvider = waiterObjectProvider;
        this.headChef = headChef;
    }

    public void processOrder(String order, int table) {
        Waiter waiter = waiterObjectProvider.getObject(UUID.randomUUID());
        waiter.takeOrder(order, table);
        headChef.makeOrder(waiter);
    }
}
