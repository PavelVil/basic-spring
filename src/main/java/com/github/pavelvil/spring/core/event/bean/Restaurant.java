package com.github.pavelvil.spring.core.event.bean;

import com.github.pavelvil.spring.core.event.event.OrderEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {

    private final ApplicationEventPublisher eventPublisher;

    public Restaurant(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void placeOrder(String orderDetails) {
        eventPublisher.publishEvent(new OrderEvent(this, orderDetails));
    }

}
