package com.github.pavelvil.spring.core.spel.bean;

import com.github.pavelvil.spring.core.spel.annotation.Sentence;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    @Sentence(expression = "setStaticGreetingMessage(#bean)")
    private String greeting;

    public void printGreetings() {
        System.out.println("Customer greet: " + greeting);
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
