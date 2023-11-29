package com.github.pavelvil.spring.core.scope;

import com.github.pavelvil.spring.core.scope.beans.HeadChef;
import com.github.pavelvil.spring.core.scope.beans.ObjectProviderExample;
import com.github.pavelvil.spring.core.scope.beans.OrderProcessor;
import com.github.pavelvil.spring.core.scope.beans.Waiter;
import com.github.pavelvil.spring.core.scope.config.RestaurantScopeConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class RestaurantApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestaurantScopeConfig.class);

        HeadChef first = context.getBean(HeadChef.class);
        HeadChef second = context.getBean(HeadChef.class);
        first.setName("Alex");
        System.out.println(first);
        System.out.println(second);

        Waiter firstWaiter = context.getBean(Waiter.class, UUID.randomUUID());
        Waiter secondWaiter = context.getBean(Waiter.class, UUID.randomUUID());

        firstWaiter.setOrder("Coffee");
        System.out.println(firstWaiter);
        System.out.println(secondWaiter);

        ObjectProviderExample objectProviderExample = context.getBean(ObjectProviderExample.class);
        objectProviderExample.getAndCompareHeadChef();
        objectProviderExample.getAndCompareWaiter();

        OrderProcessor processor = context.getBean(OrderProcessor.class);
        processor.processOrder("Coffee", 9);
    }
}
