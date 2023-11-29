package com.github.pavelvil.spring.core.scope.beans;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectProviderExample {

    private final ObjectProvider<Waiter> waiterObjectProvider;

    private final ObjectProvider<HeadChef> headChefObjectProvider;

    public ObjectProviderExample(ObjectProvider<Waiter> waiterObjectProvider, ObjectProvider<HeadChef> headChefObjectProvider) {
        this.waiterObjectProvider = waiterObjectProvider;
        this.headChefObjectProvider = headChefObjectProvider;
    }


    public void getAndCompareHeadChef() {
        HeadChef first = headChefObjectProvider.getObject();
        HeadChef second = headChefObjectProvider.getObject();

        System.out.println("HeadChef is equals: " + (first == second));
    }

    public void getAndCompareWaiter() {
        Waiter first = waiterObjectProvider.getObject(UUID.randomUUID());
        Waiter second = waiterObjectProvider.getObject(UUID.randomUUID());

        System.out.println("Waiter is equals: " + (first == second));
    }
}
