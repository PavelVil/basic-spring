package com.github.pavelvil.spring.core.spel.processor;

import com.github.pavelvil.spring.core.spel.bean.Customer;
import com.github.pavelvil.spring.core.spel.bean.Waiter;

import java.util.HashMap;
import java.util.Map;

public class SentenceRootObject {

    private static final Map<Class<?>, String> GREETING_MESSAGES = new HashMap<>();

    static {
        GREETING_MESSAGES.put(Waiter.class, "Hello! I'm your waiter today!");
        GREETING_MESSAGES.put(Customer.class, "Hello! Can I place an order?");
    }

    public void setGreetingMessage(Object bean) {
        setMessageFor(bean);
    }

    public static void setStaticGreetingMessage(Object bean) {
        setMessageFor(bean);
    }

    private static void setMessageFor(Object bean) {
        if (!GREETING_MESSAGES.containsKey(bean.getClass())) {
            return;
        }

        if (Waiter.class.equals(bean.getClass())) {
            ((Waiter) bean).setGreeting(GREETING_MESSAGES.get(bean.getClass()));
        } else {
            ((Customer) bean).setGreeting(GREETING_MESSAGES.get(bean.getClass()));
        }
    }

}
