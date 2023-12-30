package com.github.pavelvil.spring.core.event.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;

@Component
public class Chef {

    private final BlockingQueue<String> orderQueue;

    public Chef(BlockingQueue<String> orderQueue) {
        this.orderQueue = orderQueue;
    }

    @PostConstruct
    public void prepareChef() {
        new Thread(this::processOrder).start();
    }

    public void processOrder() {
        try {
            while (true) {
                String orderDetails = orderQueue.take();

                System.out.println("Chef is preparing: " + orderDetails);

                Thread.sleep(2000);

                System.out.println("Chef finished preparing: " + orderDetails);
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
