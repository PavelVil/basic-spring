package com.github.pavelvil.spring.core.profile.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("latte")
//@Primary
public class Latte implements Coffee {
    @Override
    public String brew() {
        return "Latte";
    }
}
