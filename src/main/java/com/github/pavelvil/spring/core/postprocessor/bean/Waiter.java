package com.github.pavelvil.spring.core.postprocessor.bean;

import org.springframework.stereotype.Component;

@Component
public class Waiter {

    private boolean onDuty;

    public void takeOrder() {
        if (onDuty) {
            System.out.println("I can take order");
        } else {
            System.out.println("I'm not on duty");
        }
    }

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }
}
