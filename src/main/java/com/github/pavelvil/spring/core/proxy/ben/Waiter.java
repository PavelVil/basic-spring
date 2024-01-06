package com.github.pavelvil.spring.core.proxy.ben;

import java.time.Duration;

public class Waiter implements IWaiter {

    private final String name;

    public Waiter(String name) {
        this.name = name;
    }

    @Override
    public void serve(String customerName) {
        System.out.println(name + " is serving customer " + customerName);
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " is free");
    }
}
