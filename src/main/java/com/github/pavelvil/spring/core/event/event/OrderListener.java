package com.github.pavelvil.spring.core.event.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class OrderListener {

    private final BlockingQueue<String> orderQueue;

    public OrderListener(BlockingQueue<String> orderQueue) {
        this.orderQueue = orderQueue;
    }

    @EventListener(OrderEvent.class)
    public void onOrderEvent(OrderEvent event) {
        String orderDetails = event.getOrderDetails();
        try {
            orderQueue.put(orderDetails);
            System.out.println("New order received to listener: " + orderDetails);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
